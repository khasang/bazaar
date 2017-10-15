package io.khasang.bazaar.service.impl;

import com.inflationinrussia.webservices.GetValueChange;
import com.inflationinrussia.webservices.WebServices;
import com.inflationinrussia.webservices.WebServicesSoap;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

@Component
public class InflationService {

    private final static URL WEBSERVICES_WSDL_LOCATION;
    private final static QName WEBSERVICES_QNAME = new QName("http://www.inflationinrussia.com/WebServices",
            "WebServices");
    private WebServicesSoap inflation;

    static {
        URL url = null;
        try {
            url = new URL("http://xn----ctbjnaatncev9av3a8f8b.xn--p1ai/DesktopModules/WebServices.asmx?WSDL");
        } catch (IOException e) {
            e.printStackTrace();
        }
        WEBSERVICES_WSDL_LOCATION = url;
    }

    {
        Service service = Service.create(WEBSERVICES_WSDL_LOCATION, WEBSERVICES_QNAME);
        inflation = service.getPort(WebServicesSoap.class);
    }

    public String getValueChange(String startAmount, String startMonth, String endMonth) {

        GetValueChange valueChange = new GetValueChange();
        valueChange.setStartAmount(new BigDecimal(startAmount));
            valueChange.setStartMonth(getXmlGregorianCalendar(startMonth));
            valueChange.setEndMonth(getXmlGregorianCalendar(endMonth));
        valueChange.setApplyDenominationOf1998(true);
        BigDecimal resultValueChange = inflation.getValueChange(valueChange.getStartAmount(), valueChange.getStartMonth(),
                valueChange.getEndMonth(), valueChange.isApplyDenominationOf1998());
        return resultValueChange.toString();
    }

    private XMLGregorianCalendar getXmlGregorianCalendar(String month) {
        XMLGregorianCalendar xmlGregorianCalendar =null;
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            Date startDate = new SimpleDateFormat("yyyy.MM", Locale.ENGLISH).parse(month);
            gregorianCalendar.setTime(startDate);
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (ParseException | DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return xmlGregorianCalendar;
    }
}
