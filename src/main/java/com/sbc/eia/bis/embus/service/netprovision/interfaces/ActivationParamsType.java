//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.11.17 at 11:24:08 PST 
//


package com.sbc.eia.bis.embus.service.netprovision.interfaces;


/**
 * Java content class for ActivationParamsType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/E:/JAXB/netprovision/xml/NetProvision_20_0_0_002.xsd line 174)
 * <p>
 * <pre>
 * &lt;complexType name="ActivationParamsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NetworkDelivery" type="{http://www.syndesis.com/NN/XSNN}BooleanEnum"/>
 *         &lt;element name="Priority" type="{http://www.syndesis.com/NN/XSNN}priority_int"/>
 *         &lt;element name="DescriptionPolicy" type="{http://www.syndesis.com/NN/XSNN}long_string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface ActivationParamsType {


    /**
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getNetworkDelivery();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setNetworkDelivery(java.lang.String value);

    boolean isSetNetworkDelivery();

    void unsetNetworkDelivery();

    /**
     * 
     */
    int getPriority();

    /**
     * 
     */
    void setPriority(int value);

    boolean isSetPriority();

    void unsetPriority();

    /**
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getDescriptionPolicy();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setDescriptionPolicy(java.lang.String value);

    boolean isSetDescriptionPolicy();

    void unsetDescriptionPolicy();

}
