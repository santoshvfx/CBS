//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2004.09.09 at 11:03:57 PDT 
//


package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces;


/**
 * Java content class for AttributeResultItemSeqSeqType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/bis_jaxb/npconnector_rmbisrequests/xml/NPConnector_2.1.0.xsd line 286)
 * <p>
 * <pre>
 * &lt;complexType name="AttributeResultItemSeqSeqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attributeResultItemList" type="{http://www.sbc.com/eia/rcl/netprovision}AttributeResultItemSeqType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface AttributeResultItemSeqSeqType {


    /**
     * Gets the value of the AttributeResultItemList property.
     * 
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the AttributeResultItemList property.
     * 
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeResultItemList().add(newItem);
     * </pre>
     * 
     * 
     * Objects of the following type(s) are allowed in the list
     * {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AttributeResultItemSeqType}
     * 
     */
    java.util.List getAttributeResultItemList();

}
