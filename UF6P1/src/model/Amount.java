package model;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name="amount")
public class Amount {
    private double value;
    private String currency;

    public Amount(double value, String currency) {
        this.value = value;
        this.currency = "€";
    }
    
    public Amount() {
		super();
	}


	@XmlAttribute
    public double getValue() {
        return value;
    }

    public void setValue(double value, String string) {
        this.value = value;
    }
    
    @XmlAttribute
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String toString() {
        return value + currency;
    }
    
    public static Amount valueOf(String value2) {
    	return null;
    }
}