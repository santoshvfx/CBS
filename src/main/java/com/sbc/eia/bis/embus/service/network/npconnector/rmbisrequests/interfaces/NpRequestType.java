//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2004.09.09 at 11:03:57 PDT 
//


package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces;


/**
 * Java content class for NpRequestType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/bis_jaxb/npconnector_rmbisrequests/xml/NPConnector_2.1.0.xsd line 13)
 * <p>
 * <pre>
 * &lt;complexType name="NpRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="createServiceObjectRequest" type="{http://www.sbc.com/eia/rcl/netprovision}CreateServiceObjectRequestType"/>
 *         &lt;element name="modifyServiceObjectRequest" type="{http://www.sbc.com/eia/rcl/netprovision}ModifyServiceObjectRequestType"/>
 *         &lt;element name="deleteServiceObjectRequest" type="{http://www.sbc.com/eia/rcl/netprovision}DeleteServiceObjectRequestType"/>
 *         &lt;element name="getAllServiceObjectAttributesRequest" type="{http://www.sbc.com/eia/rcl/netprovision}GetAllServiceObjectAttributesRequestType"/>
 *         &lt;element name="createServiceElementRequest" type="{http://www.sbc.com/eia/rcl/netprovision}CreateServiceElementRequestType"/>
 *         &lt;element name="modifyServiceElementRequest" type="{http://www.sbc.com/eia/rcl/netprovision}ModifyServiceElementRequestType"/>
 *         &lt;element name="deleteServiceElementRequest" type="{http://www.sbc.com/eia/rcl/netprovision}DeleteServiceElementRequestType"/>
 *         &lt;element name="getServiceElementAttributesRequest" type="{http://www.sbc.com/eia/rcl/netprovision}GetServiceElementAttributesRequestType"/>
 *         &lt;element name="listerRequest" type="{http://www.sbc.com/eia/rcl/netprovision}ListerRequestType"/>
 *         &lt;element name="getAllErrorsRequest" type="{http://www.sbc.com/eia/rcl/netprovision}GetAllErrorsRequestType"/>
 *         &lt;element name="getSessionIdRequest" type="{http://www.sbc.com/eia/rcl/netprovision}GetSessionIdRequestType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface NpRequestType {


    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.DeleteServiceElementRequestType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.DeleteServiceElementRequestType getDeleteServiceElementRequest();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.DeleteServiceElementRequestType}
     */
    void setDeleteServiceElementRequest(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.DeleteServiceElementRequestType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.CreateServiceObjectRequestType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.CreateServiceObjectRequestType getCreateServiceObjectRequest();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.CreateServiceObjectRequestType}
     */
    void setCreateServiceObjectRequest(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.CreateServiceObjectRequestType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ModifyServiceElementRequestType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ModifyServiceElementRequestType getModifyServiceElementRequest();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ModifyServiceElementRequestType}
     */
    void setModifyServiceElementRequest(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ModifyServiceElementRequestType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetAllServiceObjectAttributesRequestType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetAllServiceObjectAttributesRequestType getGetAllServiceObjectAttributesRequest();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetAllServiceObjectAttributesRequestType}
     */
    void setGetAllServiceObjectAttributesRequest(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetAllServiceObjectAttributesRequestType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.DeleteServiceObjectRequestType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.DeleteServiceObjectRequestType getDeleteServiceObjectRequest();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.DeleteServiceObjectRequestType}
     */
    void setDeleteServiceObjectRequest(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.DeleteServiceObjectRequestType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetServiceElementAttributesRequestType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetServiceElementAttributesRequestType getGetServiceElementAttributesRequest();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetServiceElementAttributesRequestType}
     */
    void setGetServiceElementAttributesRequest(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetServiceElementAttributesRequestType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetSessionIdRequestType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetSessionIdRequestType getGetSessionIdRequest();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetSessionIdRequestType}
     */
    void setGetSessionIdRequest(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetSessionIdRequestType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.CreateServiceElementRequestType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.CreateServiceElementRequestType getCreateServiceElementRequest();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.CreateServiceElementRequestType}
     */
    void setCreateServiceElementRequest(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.CreateServiceElementRequestType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ModifyServiceObjectRequestType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ModifyServiceObjectRequestType getModifyServiceObjectRequest();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ModifyServiceObjectRequestType}
     */
    void setModifyServiceObjectRequest(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ModifyServiceObjectRequestType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetAllErrorsRequestType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetAllErrorsRequestType getGetAllErrorsRequest();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetAllErrorsRequestType}
     */
    void setGetAllErrorsRequest(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetAllErrorsRequestType value);

    /**
     * 
     * @return
     *     possible object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ListerRequestType}
     */
    com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ListerRequestType getListerRequest();

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ListerRequestType}
     */
    void setListerRequest(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ListerRequestType value);

}
