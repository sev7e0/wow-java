package com.sev7e0.wow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Stream;

/**
 * Title: URLTest.java
 * description:
 *
 * @author Lijiaqi
 * @version 1.0
 * @since 2018-11-29 10:57
 **/

public class URLTest {

    /**
     * logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(URLTest.class);


    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 18; i++) {
            LOG.info(String.valueOf(i%9));
        }



        URL url = new URL("http://www.baidu.com");

        InputStreamReader inputStream = new InputStreamReader(url.openStream());
        BufferedReader br = new BufferedReader(inputStream);
        Stream<String> lines = br.lines();
        Stream<String> objectStream = lines.flatMap(line -> Stream.of(line.split("\n")));
        objectStream.forEach(LOG::info);
        br.close();

    }
}
