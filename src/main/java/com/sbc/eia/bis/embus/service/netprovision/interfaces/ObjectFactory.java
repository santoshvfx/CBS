//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.11.17 at 11:24:08 PST 
//


package com.sbc.eia.bis.embus.service.netprovision.interfaces;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sbc.eia.bis.embus.service.netprovision.interfaces package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
public class ObjectFactory
    extends com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.DefaultJAXBContextImpl
{

    private static java.util.HashMap defaultImplementations = new java.util.HashMap();
    private static java.util.HashMap rootTagMap = new java.util.HashMap();
    public final static com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.GrammarInfo grammarInfo = new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.GrammarInfoImpl(rootTagMap, defaultImplementations, (com.sbc.eia.bis.embus.service.netprovision.interfaces.ObjectFactory.class));
    public final static java.lang.Class version = (com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.JAXBVersion.class);

    static {
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.ErrorDetailsMessage.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ErrorDetailsMessageImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.ErrorMessage.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ErrorMessageImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.RequestType.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestTypeImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.ElementItem.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ElementItemImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.ActivationParamsType.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ActivationParamsTypeImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.ErrorDetailType.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ErrorDetailTypeImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.ResponseItem.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ResponseItemImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.ObjectItem.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ObjectItemImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.ResponseType.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ResponseTypeImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.ExceptionResponseType.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseTypeImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.RequestItem.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestItemImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.ExceptionResponse.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.Request.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestImpl");
        defaultImplementations.put((com.sbc.eia.bis.embus.service.netprovision.interfaces.Response.class), "com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ResponseImpl");
        rootTagMap.put(new javax.xml.namespace.QName("http://www.syndesis.com/NN/XSNN", "ExceptionResponse"), (com.sbc.eia.bis.embus.service.netprovision.interfaces.ExceptionResponse.class));
        rootTagMap.put(new javax.xml.namespace.QName("http://www.syndesis.com/NN/XSNN", "Request"), (com.sbc.eia.bis.embus.service.netprovision.interfaces.Request.class));
        rootTagMap.put(new javax.xml.namespace.QName("http://www.syndesis.com/NN/XSNN", "Response"), (com.sbc.eia.bis.embus.service.netprovision.interfaces.Response.class));
    }

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sbc.eia.bis.embus.service.netprovision.interfaces
     * 
     */
    public ObjectFactory() {
        super(grammarInfo);
    }

    /**
     * Create an instance of the specified Java content interface.
     * 
     * @param javaContentInterface
     *     the Class object of the javacontent interface to instantiate
     * @return
     *     a new instance
     * @throws JAXBException
     *     if an error occurs
     */
    public java.lang.Object newInstance(java.lang.Class javaContentInterface)
        throws javax.xml.bind.JAXBException
    {
        return super.newInstance(javaContentInterface);
    }

    /**
     * Get the specified property. This method can only be
     * used to get provider specific properties.
     * Attempting to get an undefined property will result
     * in a PropertyException being thrown.
     * 
     * @param name
     *     the name of the property to retrieve
     * @return
     *     the value of the requested property
     * @throws PropertyException
     *     when there is an error retrieving the given property or value
     */
    public java.lang.Object getProperty(java.lang.String name)
        throws javax.xml.bind.PropertyException
    {
        return super.getProperty(name);
    }

    /**
     * Set the specified property. This method can only be
     * used to set provider specific properties.
     * Attempting to set an undefined property will result
     * in a PropertyException being thrown.
     * 
     * @param value
     *     the value of the property to be set
     * @param name
     *     the name of the property to retrieve
     * @throws PropertyException
     *     when there is an error processing the given property or value
     */
    public void setProperty(java.lang.String name, java.lang.Object value)
        throws javax.xml.bind.PropertyException
    {
        super.setProperty(name, value);
    }

    /**
     * Create an instance of ErrorDetailsMessage
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.ErrorDetailsMessage createErrorDetailsMessage()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ErrorDetailsMessageImpl();
    }

    /**
     * Create an instance of ErrorMessage
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.ErrorMessage createErrorMessage()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ErrorMessageImpl();
    }

    /**
     * Create an instance of RequestType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.RequestType createRequestType()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestTypeImpl();
    }

    /**
     * Create an instance of ElementItem
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.ElementItem createElementItem()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ElementItemImpl();
    }

    /**
     * Create an instance of ActivationParamsType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.ActivationParamsType createActivationParamsType()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ActivationParamsTypeImpl();
    }

    /**
     * Create an instance of ErrorDetailType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.ErrorDetailType createErrorDetailType()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ErrorDetailTypeImpl();
    }

    /**
     * Create an instance of ResponseItem
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.ResponseItem createResponseItem()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ResponseItemImpl();
    }

    /**
     * Create an instance of ObjectItem
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.ObjectItem createObjectItem()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ObjectItemImpl();
    }

    /**
     * Create an instance of ResponseType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.ResponseType createResponseType()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ResponseTypeImpl();
    }

    /**
     * Create an instance of ExceptionResponseType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.ExceptionResponseType createExceptionResponseType()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseTypeImpl();
    }

    /**
     * Create an instance of RequestItem
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.RequestItem createRequestItem()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestItemImpl();
    }

    /**
     * Create an instance of ExceptionResponse
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.ExceptionResponse createExceptionResponse()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseImpl();
    }

    /**
     * Create an instance of Request
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.Request createRequest()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestImpl();
    }

    /**
     * Create an instance of Response
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public com.sbc.eia.bis.embus.service.netprovision.interfaces.Response createResponse()
        throws javax.xml.bind.JAXBException
    {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ResponseImpl();
    }

}
