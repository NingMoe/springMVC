package com.lph.app.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

    private String name;
    private String text;

    public Message(String name, String text) {
	this.name = name;
	this.text = text;
    }

    @XmlElement
    public String getName() {
	return name;
    }

    @XmlElement
    public String getText() {
	return text;
    }

    public Message() {
	super();
	// TODO Auto-generated constructor stub
    }

}