//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2004.09.09 at 11:03:57 PDT 
//


package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl;

public class AVExceptionTypeImpl implements com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AVExceptionType, com.sun.xml.bind.JAXBObject, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallableObject, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializable, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.ValidatableObject
{

    protected java.lang.String _AbandonedTransactionId;
    protected java.lang.String _Message;
    protected java.lang.String _Category;
    protected com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AVExceptionDetailsType _Details;
    public final static java.lang.Class version = (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AVExceptionType.class);
    }

    public java.lang.String getAbandonedTransactionId() {
        return _AbandonedTransactionId;
    }

    public void setAbandonedTransactionId(java.lang.String value) {
        _AbandonedTransactionId = value;
    }

    public java.lang.String getMessage() {
        return _Message;
    }

    public void setMessage(java.lang.String value) {
        _Message = value;
    }

    public java.lang.String getCategory() {
        return _Category;
    }

    public void setCategory(java.lang.String value) {
        _Category = value;
    }

    public com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AVExceptionDetailsType getDetails() {
        return _Details;
    }

    public void setDetails(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AVExceptionDetailsType value) {
        _Details = value;
    }

    public com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingContext context) {
        return new com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("", "category");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _Category), "Category");
        } catch (java.lang.Exception e) {
            com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "message");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _Message), "Message");
        } catch (java.lang.Exception e) {
            com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "details");
        context.childAsURIs(((com.sun.xml.bind.JAXBObject) _Details), "Details");
        context.endNamespaceDecls();
        context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _Details), "Details");
        context.endAttributes();
        context.childAsBody(((com.sun.xml.bind.JAXBObject) _Details), "Details");
        context.endElement();
        context.startElement("", "abandonedTransactionId");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _AbandonedTransactionId), "AbandonedTransactionId");
        } catch (java.lang.Exception e) {
            com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
    }

    public void serializeAttributes(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public void serializeURIs(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AVExceptionType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava"
+"/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\rc\u00ec0ppsq\u0000~\u0000\u0000\n\u00bc\u0093\u00dbppsq\u0000~\u0000\u0000\u0006"
+"\u00cb\u0092\u00ffppsr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L"
+"\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv"
+".grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL"
+"\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0003Y\t\u00bdpp\u0000sq\u0000~\u0000\u0000\u0003Y\t\u00b2ppsr\u0000\u001bcom.sun.msv."
+"grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Data"
+"type;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq"
+"\u0000~\u0000\u0003\u0000 \u00fa\u0093ppsr\u0000)com.sun.msv.datatype.xsd.EnumerationFacet\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0006valuest\u0000\u000fLjava/util/Set;xr\u00009com.sun.msv.datatype.xs"
+"d.DataTypeWithValueConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv"
+".datatype.xsd.DataTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012"
+"needValueCheckFlagL\u0000\bbaseTypet\u0000)Lcom/sun/msv/datatype/xsd/XS"
+"DatatypeImpl;L\u0000\fconcreteTypet\u0000\'Lcom/sun/msv/datatype/xsd/Con"
+"creteType;L\u0000\tfacetNamet\u0000\u0012Ljava/lang/String;xr\u0000\'com.sun.msv.d"
+"atatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000\u0017L\u0000\b"
+"typeNameq\u0000~\u0000\u0017L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/White"
+"SpaceProcessor;xpt\u0000\'http://www.sbc.com/eia/rcl/netprovisiont"
+"\u0000\fCategoryTypesr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcess"
+"or$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpac"
+"eProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0000\u0000sr\u0000#com.sun.msv.datatype.xsd.String"
+"Type\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xsd."
+"BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.Con"
+"creteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0018t\u0000 http://www.w3.org/2001/XMLSchem"
+"at\u0000\u0006stringq\u0000~\u0000\u001f\u0001q\u0000~\u0000#t\u0000\u000benumerationsr\u0000\u0011java.util.HashSet\u00baD\u0085\u0095"
+"\u0096\u00b8\u00b74\u0003\u0000\u0000xpw\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0006t\u0000\tsucceededt\u0000\ffatalFailuret\u0000\u0013succeed"
+"edWithStatust\u0000\u0012failedWaitAndRetryt\u0000\rfailedNoRetryt\u0000\u0015failedCo"
+"rrectAndRetryxsr\u00000com.sun.msv.grammar.Expression$NullSetExpr"
+"ession\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\nppsr\u0000\u001bcom.sun.msv.util.StringPair"
+"\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0017L\u0000\fnamespaceURIq\u0000~\u0000\u0017xpq\u0000~\u0000\u001cq\u0000~\u0000\u001b"
+"sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001\u00038\u000f\u001appsr\u0000 "
+"com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnam"
+"eClassq\u0000~\u0000\txq\u0000~\u0000\u0003\u00038\u000f\u000fsr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valu"
+"exp\u0000psq\u0000~\u0000\r\u0001\u0093\u00f6\u00f2ppsr\u0000\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000!q\u0000~\u0000$t\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.WhiteS"
+"paceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001eq\u0000~\u00000sq\u0000~\u00001q\u0000~\u0000<q\u0000~\u0000$"
+"sr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNa"
+"meq\u0000~\u0000\u0017L\u0000\fnamespaceURIq\u0000~\u0000\u0017xr\u0000\u001dcom.sun.msv.grammar.NameClass"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-inst"
+"ancesr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tsq\u0000~\u00007\u0001psq\u0000~\u0000@t\u0000\bcategoryt\u0000\u0000sq\u0000~\u0000\b\u0003r\u0089=pp\u0000sq"
+"\u0000~\u0000\u0000\u0003r\u00892ppsq\u0000~\u0000\r\u0000s0lppq\u0000~\u0000#q\u0000~\u00000sq\u0000~\u00001q\u0000~\u0000%q\u0000~\u0000$sq\u0000~\u00003\u0002\u00ffX\u00c1pp"
+"sq\u0000~\u00005\u0002\u00ffX\u00b6q\u0000~\u00008pq\u0000~\u00009sq\u0000~\u0000@q\u0000~\u0000Cq\u0000~\u0000Dq\u0000~\u0000Fsq\u0000~\u0000@t\u0000\u0007messageq\u0000"
+"~\u0000Jsq\u0000~\u0000\b\u0003\u00f1\u0000\u00d7pp\u0000sq\u0000~\u0000\u0000\u0003\u00f1\u0000\u00ccppsq\u0000~\u0000\b\u0000gT\u00f8pp\u0000sq\u0000~\u00003\u0000gT\u00edppsr\u0000 com"
+".sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.gram"
+"mar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0000gT\u00e2q\u0000~\u00008psq\u0000~\u00005\u0000gT\u00df"
+"q\u0000~\u00008psr\u00002com.sun.msv.grammar.Expression$AnyStringExpression"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\bq\u0000~\u0000Gq\u0000~\u0000]sr\u0000 com.sun.msv.grammar.AnyNa"
+"meClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Aq\u0000~\u0000Fsq\u0000~\u0000@t\u0000acom.sbc.eia.bis.embus."
+"service.network.npconnector.rmbisrequests.interfaces.AVExcep"
+"tionDetailsTypet\u0000+http://java.sun.com/jaxb/xjc/dummy-element"
+"ssq\u0000~\u00003\u0003\u0089\u00ab\u00cfppsq\u0000~\u00005\u0003\u0089\u00ab\u00c4q\u0000~\u00008pq\u0000~\u00009sq\u0000~\u0000@q\u0000~\u0000Cq\u0000~\u0000Dq\u0000~\u0000Fsq\u0000~\u0000"
+"@t\u0000\u0007detailsq\u0000~\u0000Jsq\u0000~\u0000\b\u0002\u00a7XPpp\u0000sq\u0000~\u0000\u0000\u0002\u00a7XEppq\u0000~\u0000Msq\u0000~\u00003\u00024\'\u00d4ppsq"
+"\u0000~\u00005\u00024\'\u00c9q\u0000~\u00008pq\u0000~\u00009sq\u0000~\u0000@q\u0000~\u0000Cq\u0000~\u0000Dq\u0000~\u0000Fsq\u0000~\u0000@t\u0000\u0016abandonedTr"
+"ansactionIdq\u0000~\u0000Jsr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$Close"
+"dHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0"
+"N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthresholdL\u0000\u0006parentq\u0000~\u0000p[\u0000\u0005tablet\u0000![Lcom/s"
+"un/msv/grammar/Expression;xp\u0000\u0000\u0000\r\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.gramm"
+"ar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfppppppppppppppq\u0000~\u0000ipq\u0000~\u0000\u0006pq\u0000~"
+"\u0000Lppppppppppppppq\u0000~\u0000Zpppq\u0000~\u0000\fppppppq\u0000~\u0000Wpq\u0000~\u0000\u0005ppq\u0000~\u0000cppppppp"
+"q\u0000~\u0000jpppq\u0000~\u0000Oppppppppppppppppppppq\u0000~\u0000\u0007ppppppq\u0000~\u00004ppppppppppp"
+"ppppppppq\u0000~\u0000Uppppppppppppppppppppppppppppppppppppppppppppppp"
+"pppppppppppppppppppppppppppppppppp"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingContext context) {
            super(context, "-------------");
        }

        protected Unmarshaller(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  3 :
                        if (("message" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        break;
                    case  6 :
                        if (("details" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 7;
                            return ;
                        }
                        break;
                    case  12 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  7 :
                        if (("stack" == ___local)&&("" == ___uri)) {
                            _Details = ((com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionDetailsTypeImpl) spawnChildFromEnterElement((com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionDetailsTypeImpl.class), 8, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("save" == ___local)&&("" == ___uri)) {
                            _Details = ((com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionDetailsTypeImpl) spawnChildFromEnterElement((com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionDetailsTypeImpl.class), 8, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("numbered" == ___local)&&("" == ___uri)) {
                            _Details = ((com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionDetailsTypeImpl) spawnChildFromEnterElement((com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionDetailsTypeImpl.class), 8, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("unNumbered" == ___local)&&("" == ___uri)) {
                            _Details = ((com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionDetailsTypeImpl) spawnChildFromEnterElement((com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionDetailsTypeImpl.class), 8, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        break;
                    case  9 :
                        if (("abandonedTransactionId" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 10;
                            return ;
                        }
                        break;
                    case  0 :
                        if (("category" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        break;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  8 :
                        if (("details" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  12 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  11 :
                        if (("abandonedTransactionId" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  2 :
                        if (("category" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  5 :
                        if (("message" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  12 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  12 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  12 :
                            revertToParentFromText(value);
                            return ;
                        case  4 :
                            eatText1(value);
                            state = 5;
                            return ;
                        case  10 :
                            eatText2(value);
                            state = 11;
                            return ;
                        case  1 :
                            eatText3(value);
                            state = 2;
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Message = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _AbandonedTransactionId = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Category = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
