//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.11.17 at 11:24:08 PST 
//


package com.sbc.eia.bis.embus.service.netprovision.interfaces.impl;

public class ErrorMessageImpl implements com.sbc.eia.bis.embus.service.netprovision.interfaces.ErrorMessage, java.io.Serializable, com.sun.xml.bind.JAXBObject, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallableObject, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializable, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.ValidatableObject
{

    private final static long serialVersionUID = 5000L;
    protected java.lang.String _Severity;
    protected java.lang.String _ExceptionType;
    protected java.lang.String _Message;
    protected java.lang.String _MsgId;
    public final static java.lang.Class version = (com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sbc.eia.bis.embus.service.netprovision.interfaces.ErrorMessage.class);
    }

    public java.lang.String getSeverity() {
        return _Severity;
    }

    public void setSeverity(java.lang.String value) {
        _Severity = value;
    }

    public boolean isSetSeverity() {
        return (_Severity!= null);
    }

    public void unsetSeverity() {
        _Severity = null;
    }

    public java.lang.String getExceptionType() {
        return _ExceptionType;
    }

    public void setExceptionType(java.lang.String value) {
        _ExceptionType = value;
    }

    public boolean isSetExceptionType() {
        return (_ExceptionType!= null);
    }

    public void unsetExceptionType() {
        _ExceptionType = null;
    }

    public java.lang.String getMessage() {
        return _Message;
    }

    public void setMessage(java.lang.String value) {
        _Message = value;
    }

    public boolean isSetMessage() {
        return (_Message!= null);
    }

    public void unsetMessage() {
        _Message = null;
    }

    public java.lang.String getMsgId() {
        return _MsgId;
    }

    public void setMsgId(java.lang.String value) {
        _MsgId = value;
    }

    public boolean isSetMsgId() {
        return (_MsgId!= null);
    }

    public void unsetMsgId() {
        _MsgId = null;
    }

    public com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context) {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ErrorMessageImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (_MsgId!= null) {
            context.startElement("", "MsgId");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _MsgId), "MsgId");
            } catch (java.lang.Exception e) {
                com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_Message!= null) {
            context.startElement("", "Message");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _Message), "Message");
            } catch (java.lang.Exception e) {
                com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_Severity!= null) {
            context.startElement("", "Severity");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _Severity), "Severity");
            } catch (java.lang.Exception e) {
                com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_ExceptionType!= null) {
            context.startElement("", "ExceptionType");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _ExceptionType), "ExceptionType");
            } catch (java.lang.Exception e) {
                com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
    }

    public void serializeAttributes(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public void serializeURIs(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.sbc.eia.bis.embus.service.netprovision.interfaces.ErrorMessage.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava"
+"/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\u0004\u00d5zQppsq\u0000~\u0000\u0000\u0003\u00a4\u00f9\u00c5ppsq\u0000~\u0000\u0000\u0002"
+"\u00c3Gwppsr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001\u0001(7Np"
+"psr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tna"
+"meClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.gra"
+"mmar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fco"
+"ntentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0001(7Csr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005"
+"valuexp\u0000p\u0000sq\u0000~\u0000\u0000\u0001(78ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004"
+"namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003\u0000x|&ppsr\u0000\'com.sun."
+"msv.datatype.xsd.MaxLengthFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\tmaxLengthxr\u00009co"
+"m.sun.msv.datatype.xsd.DataTypeWithValueConstraintFacet\"\u00a7Ro\u00ca"
+"\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.DataTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
+"\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueCheckFlagL\u0000\bbaseTypet\u0000)Lcom/su"
+"n/msv/datatype/xsd/XSDatatypeImpl;L\u0000\fconcreteTypet\u0000\'Lcom/sun"
+"/msv/datatype/xsd/ConcreteType;L\u0000\tfacetNamet\u0000\u0012Ljava/lang/Str"
+"ing;xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000"
+"\fnamespaceUriq\u0000~\u0000\u001aL\u0000\btypeNameq\u0000~\u0000\u001aL\u0000\nwhiteSpacet\u0000.Lcom/sun/m"
+"sv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000\u001fhttp://www.syndesis"
+".com/NN/XSNNt\u0000\rnormal_stringsr\u00005com.sun.msv.datatype.xsd.Whi"
+"teSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype"
+".xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0000\u0000sr\u0000#com.sun.msv.datat"
+"ype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv"
+".datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.da"
+"tatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001bt\u0000 http://www.w3.org"
+"/2001/XMLSchemat\u0000\u0006stringq\u0000~\u0000\"\u0001q\u0000~\u0000&t\u0000\tmaxLength\u0000\u0000\u0000@sr\u00000com.s"
+"un.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003"
+"\u0000\u0000\u0000\nppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalName"
+"q\u0000~\u0000\u001aL\u0000\fnamespaceURIq\u0000~\u0000\u001axpq\u0000~\u0000\u001fq\u0000~\u0000\u001esq\u0000~\u0000\b\u0000\u00af\u00bb\rppsr\u0000 com.sun"
+".msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq"
+"\u0000~\u0000\u000bxq\u0000~\u0000\u0003\u0000\u00af\u00bb\u0002q\u0000~\u0000\u000fpsq\u0000~\u0000\u0011\u0000A\u009d\u0015ppsr\u0000\"com.sun.msv.datatype.xsd"
+".QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000$q\u0000~\u0000\'t\u0000\u0005QNamesr\u00005com.sun.msv.data"
+"type.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000!q\u0000~\u0000+s"
+"q\u0000~\u0000,q\u0000~\u00004q\u0000~\u0000\'sr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001aL\u0000\fnamespaceURIq\u0000~\u0000\u001axr\u0000\u001dcom.sun.msv.gr"
+"ammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3.org/2001"
+"/XMLSchema-instancesr\u00000com.sun.msv.grammar.Expression$Epsilo"
+"nExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tsq\u0000~\u0000\u000e\u0001psq\u0000~\u00008t\u0000\u0005MsgIdt\u0000\u0000q\u0000~"
+"\u0000>sq\u0000~\u0000\b\u0001\u009b\u0010$ppsq\u0000~\u0000\n\u0001\u009b\u0010\u0019q\u0000~\u0000\u000fp\u0000sq\u0000~\u0000\u0000\u0001\u009b\u0010\u000eppsq\u0000~\u0000\u0011\u0001V\u008f\u00c5ppq\u0000~\u0000&"
+"q\u0000~\u0000+sq\u0000~\u0000,q\u0000~\u0000(q\u0000~\u0000\'sq\u0000~\u0000\b\u0000D\u0080Dppsq\u0000~\u0000/\u0000D\u00809q\u0000~\u0000\u000fpq\u0000~\u00001sq\u0000~\u00008"
+"q\u0000~\u0000;q\u0000~\u0000<q\u0000~\u0000>sq\u0000~\u00008t\u0000\u0007Messageq\u0000~\u0000Bq\u0000~\u0000>sq\u0000~\u0000\b\u0000\u00e1\u00b2Ippsq\u0000~\u0000\n\u0000"
+"\u00e1\u00b2>q\u0000~\u0000\u000fp\u0000sq\u0000~\u0000\u0000\u0000\u00e1\u00b23ppq\u0000~\u0000\u0014sq\u0000~\u0000\b\u0000i6\bppsq\u0000~\u0000/\u0000i5\u00fdq\u0000~\u0000\u000fpq\u0000~\u00001"
+"sq\u0000~\u00008q\u0000~\u0000;q\u0000~\u0000<q\u0000~\u0000>sq\u0000~\u00008t\u0000\bSeverityq\u0000~\u0000Bq\u0000~\u0000>sq\u0000~\u0000\b\u00010\u0080\u0087pp"
+"sq\u0000~\u0000\n\u00010\u0080|q\u0000~\u0000\u000fp\u0000sq\u0000~\u0000\u0000\u00010\u0080qppsq\u0000~\u0000\u0011\u0000\u0019\u0089\u00c5ppsq\u0000~\u0000\u0015q\u0000~\u0000\u001et\u0000\u000blong_"
+"stringq\u0000~\u0000\"\u0000\u0000q\u0000~\u0000&q\u0000~\u0000&q\u0000~\u0000)\u0000\u0000\u0000\u00ffq\u0000~\u0000+sq\u0000~\u0000,q\u0000~\u0000Zq\u0000~\u0000\u001esq\u0000~\u0000\b\u0001"
+"\u0016\u00f6\u00a7ppsq\u0000~\u0000/\u0001\u0016\u00f6\u009cq\u0000~\u0000\u000fpq\u0000~\u00001sq\u0000~\u00008q\u0000~\u0000;q\u0000~\u0000<q\u0000~\u0000>sq\u0000~\u00008t\u0000\rExce"
+"ptionTypeq\u0000~\u0000Bq\u0000~\u0000>sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$Cl"
+"osedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash"
+"\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthresholdL\u0000\u0006parentq\u0000~\u0000b[\u0000\u0005tablet\u0000![Lco"
+"m/sun/msv/grammar/Expression;xp\u0000\u0000\u0000\u000f\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.gr"
+"ammar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfq\u0000~\u0000\u0006pq\u0000~\u0000Epppppppq\u0000~\u0000Mpq\u0000"
+"~\u0000Pq\u0000~\u0000\\ppppppq\u0000~\u0000Hpppq\u0000~\u0000Cppppppppppppppppppppppppppppppppp"
+"pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp"
+"pppppppq\u0000~\u0000\u0010pppppppppppppppppppq\u0000~\u0000Wpq\u0000~\u0000\tpq\u0000~\u0000.pppppppppppp"
+"pppppq\u0000~\u0000Uppppq\u0000~\u0000\u0005pppq\u0000~\u0000\u0007ppq\u0000~\u0000Oppppppppppp"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context) {
            super(context, "-------------");
        }

        protected Unmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ErrorMessageImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  3 :
                        if (("Message" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        state = 6;
                        continue outer;
                    case  6 :
                        if (("Severity" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        state = 9;
                        continue outer;
                    case  9 :
                        if (("ExceptionType" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 10;
                            return ;
                        }
                        state = 12;
                        continue outer;
                    case  0 :
                        if (("MsgId" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        state = 3;
                        continue outer;
                    case  12 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
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
                    case  3 :
                        state = 6;
                        continue outer;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  5 :
                        if (("Message" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                    case  9 :
                        state = 12;
                        continue outer;
                    case  2 :
                        if (("MsgId" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  8 :
                        if (("Severity" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  11 :
                        if (("ExceptionType" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  0 :
                        state = 3;
                        continue outer;
                    case  12 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
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
                    case  3 :
                        state = 6;
                        continue outer;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  9 :
                        state = 12;
                        continue outer;
                    case  0 :
                        state = 3;
                        continue outer;
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
                    case  3 :
                        state = 6;
                        continue outer;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  9 :
                        state = 12;
                        continue outer;
                    case  0 :
                        state = 3;
                        continue outer;
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
                        case  1 :
                            eatText1(value);
                            state = 2;
                            return ;
                        case  7 :
                            eatText2(value);
                            state = 8;
                            return ;
                        case  3 :
                            state = 6;
                            continue outer;
                        case  6 :
                            state = 9;
                            continue outer;
                        case  9 :
                            state = 12;
                            continue outer;
                        case  10 :
                            eatText3(value);
                            state = 11;
                            return ;
                        case  4 :
                            eatText4(value);
                            state = 5;
                            return ;
                        case  0 :
                            state = 3;
                            continue outer;
                        case  12 :
                            revertToParentFromText(value);
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
                _MsgId = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Severity = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _ExceptionType = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText4(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Message = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
