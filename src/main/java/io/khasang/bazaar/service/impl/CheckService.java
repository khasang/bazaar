package io.khasang.bazaar.service.impl;

import net.yandex.speller.services.spellservice.CheckTextRequest;
import net.yandex.speller.services.spellservice.SpellServiceSoap;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.URL;

@Component
public class CheckService {

    private static final String ADDRESS = "http://speller.yandex.net/services/spellservice?WSDL";

    public String checkWord(String word) throws SOAPException, IOException {
        URL url = new URL(ADDRESS);
        QName qName = new QName("http://speller.yandex.net/services/spellservice", "SpellService");
        Service service = Service.create(url, qName);

        SpellServiceSoap hello = service.getPort(SpellServiceSoap.class);
        CheckTextRequest checkTextRequest = new CheckTextRequest();
        checkTextRequest.setText(word);
        checkTextRequest.setLang("en");
        checkTextRequest.setFormat("plain");

        if (!hello.checkText(checkTextRequest).getSpellResult().getError().isEmpty()) {
            return hello.checkText(checkTextRequest).getSpellResult().getError().get(0).getS().toString();
        } else {
            return "Word correct.";
        }
    }
}
