package com.sev7e0.wow.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Title:  OOMMetaspace.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-16 21:39
 **/

/**
 * metaspace其实由两大部分组成
 * <p>
 * Klass Metaspace
 * NoKlass Metaspace
 * Klass Metaspace就是用来存klass的，klass是我们熟知的class文件在jvm里的运行时数据结构，
 * 不过有点要提的是我们看到的类似A.class其实是存在heap里的，是java.lang.Class的一个对象实例。
 * 这块内存是紧接着Heap的，和我们之前的perm一样，这块内存大小可通过-XX:CompressedClassSpaceSize参数来控制，
 * 这个参数前面提到了默认是1G，但是这块内存也可以没有，假如没有开启压缩指针就不会有这块内存，这种情况下klass都会存在NoKlass M
 * etaspace里，另外如果我们把-Xmx设置大于32G的话，其实也是没有这块内存的，因为会这么大内存会关闭压缩指针开关。还有就是这块内存最多只会存在一块。
 * <p>
 * NoKlass Metaspace专门来存klass相关的其他的内容，比如method，constantPool等，这块内存是由多块内存组合起来的，
 * 所以可以认为是不连续的内存块组成的。这块内存是必须的，虽然叫做NoKlass Metaspace，但是也其实可以存klass的内容，上面已经提到了对应场景。
 * <p>
 * Klass Metaspace和NoKlass Mestaspace都是所有classloader共享的，所以类加载器们要分配内存，
 * 但是每个类加载器都有一个SpaceManager，来管理属于这个类加载的内存小块。如果Klass Metaspace用完了，
 * 那就会OOM了，不过一般情况下不会，NoKlass Mestaspace是由一块块内存慢慢组合起来的，在没有达到限制条件的情况下，
 * 会不断加长这条链，让它可以持续工作。
 */
public class OOMMetaspace {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(OOMMetaspace.class);

	public static void main(String[] args) {
		LOG.info("OOMMetaspace");
		URL url = null;
		List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
		try {
			url = new File("/tmp").toURI().toURL();
			URL[] urls = {url};
			while (true) {
				ClassLoader loader = new URLClassLoader(urls);
				classLoaderList.add(loader);
				loader.loadClass("com.sev7e0.wow.exception.OutOfMemoryTest");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
