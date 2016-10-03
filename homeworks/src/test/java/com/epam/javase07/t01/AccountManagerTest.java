package com.epam.javase07.t01;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-10-02.
 */
public class AccountManagerTest {

    @Test
    public void testLoadTransfers() throws IOException, SAXException, ParserConfigurationException {
        AccountManager manager=new AccountManager();
        manager.readAccountsTransferInfo("src/test/resources/Transfers.xml");




    }

}