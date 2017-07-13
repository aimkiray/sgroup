package com.shengdiyage.test.stream;

import com.shengdiyage.entity.Product;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by Akari on 2017/7/13.
 */
public class TestStream {

    public static void main(String[] args) {
        new TestStream().testObjectStream();
    }

    @Test
    public void testByteStream() {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            File file = new File("D:\\test\\poems.txt");
            if (file.exists()) {
                // 创建基于文件的输入流
                inputStream = new FileInputStream(file);
                // 创建一个字节数组存放每次读取的字节数组
                outputStream = new ByteArrayOutputStream();
                // 创建字节数组，其长度为1024
                byte[] part = new byte[1024];
                int len = 0;
                // 以字节流形式读取文件所有内容
                while ((len = inputStream.read(part)) > 0) {
                    outputStream.write(part, 0, len);
                }
                byte[] temp = outputStream.toByteArray();
                String poems = new String(temp, "utf-8");
                System.out.println(poems);
//                byte[] all = new byte[(int)file.length()];
//                inputStream.read(all);
//                String poems = all.toString();
//                System.out.println(poems);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void testReader() {
        File file = new File("D:\\test\\poems.txt");
        Reader reader = null;
        Writer writer = null;
        if (file.exists()) {
            // 创建基于文件的reader
            try {
                reader = new FileReader(file);
                writer = new CharArrayWriter();
                char[] part = new char[1024];
                int len = 0;
                while ((len = reader.read(part)) > 0) {
                    writer.write(part, 0, len);
                }
                String poems = writer.toString();
                System.out.println(poems);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void testReaderToFile() {
        File file = new File("D:\\test\\poems.txt");
        File fileCopy = new File("D:\\test\\three.txt");
        Reader reader = null;
        Writer writer = null;
        if (file.exists()) {
            // 创建基于文件的reader
            try {
                reader = new FileReader(file);
                writer = new FileWriter(fileCopy);
                char[] part = new char[1024];
                int len = 0;
                while ((len = reader.read(part)) > 0) {
                    writer.write(part, 0, len);
                }
//                String poems = writer.toString();
                System.out.println("ok");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void testObjectStream() {
        Product product = new Product("switch", 1, 1, new Date());
        File file = new File("D:\\test\\product.txt");
        if (file.exists()) {
            try (
                    // 创建对象输出流
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    // 创建对象输入流
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ) {
                objectOutputStream.writeObject(product);
                Product newProduct = (Product) objectInputStream.readObject();
                System.out.println(newProduct.getProductName());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
