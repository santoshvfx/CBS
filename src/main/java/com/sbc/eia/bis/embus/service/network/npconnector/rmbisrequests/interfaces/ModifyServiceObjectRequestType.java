//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2004.09.09 at 11:03:57 PDT 
//


package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces;


/**
 * Java content class for ModifyServiceObjectRequestType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/bis_jaxb/npconnector_rmbisrequests/xml/NPConnector_2.1.0.xsd line 90)
 * <p>
 * <pre>
 * &lt;complexType name="ModifyServiceObjectRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="attributeList" type="{http://www.sbc.com/eia/rcl/netprovision}AttributeValueItemSeqType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface ModifyServiceObjectRequestType {


    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AttributeValueItemSeqType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AttributeValueItemSeqType getAttributeList();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AttributeValueItemSeqType}
     */
    void setAttributeList(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AttributeValueItemSeqType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getClassName();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setClassName(java.lang.String value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getId();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setId(java.lang.String value);

}