//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2004.09.09 at 11:03:57 PDT 
//


package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces;


/**
 * Java content class for LogMessageType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/bis_jaxb/npconnector_rmbisrequests/xml/NPConnector_2.1.0.xsd line 473)
 * <p>
 * <pre>
 * &lt;complexType name="LogMessageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="message" type="{http://www.sbc.com/eia/rcl/netprovision}ErrorMessageType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface LogMessageType {


    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ErrorMessageType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ErrorMessageType getMessage();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ErrorMessageType}
     */
    void setMessage(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ErrorMessageType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getTimestamp();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setTimestamp(java.lang.String value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getSource();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setSource(java.lang.String value);

}
