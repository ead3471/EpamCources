package com.epam.javase04.t03;

import java.io.*;

/**
 * Created by Freemind on 2016-09-25.
 * Дан файл, содержащий буквы текст на кириллице. Кодировка файла utf-8. Прочитайте
 *информацию из файла и перепишите ее в файл в кодировкой utf-16.
 */
public class Utf8ToUtf16Decoder {
    public void decodeFile(String utf8FileName,String utf16FileName) throws IOException {
        try(
                InputStreamReader utf8Reader=new InputStreamReader(new FileInputStream(utf8FileName),"utf-8");
                OutputStreamWriter utf16Writer=new OutputStreamWriter(new FileOutputStream(utf16FileName),"utf-16")
        ){
            char[] buffer=new char[1024];
            int readedCharsCount;
            while((readedCharsCount=utf8Reader.read(buffer))>0)
            {
                utf16Writer.write(buffer,0,readedCharsCount);
            }
        }
    }
}
