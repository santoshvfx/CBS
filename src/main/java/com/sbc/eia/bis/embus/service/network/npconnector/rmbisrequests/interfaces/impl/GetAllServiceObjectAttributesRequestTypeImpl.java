//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2004.09.09 at 11:03:57 PDT 
//


package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl;

public class GetAllServiceObjectAttributesRequestTypeImpl implements com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetAllServiceObjectAttributesRequestType, com.sun.xml.bind.JAXBObject, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallableObject, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializable, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.ValidatableObject
{

    protected java.lang.String _ClassName;
    protected java.lang.String _Id;
    public final static java.lang.Class version = (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetAllServiceObjectAttributesRequestType.class);
    }

    public java.lang.String getClassName() {
        return _ClassName;
    }

    public void setClassName(java.lang.String value) {
        _ClassName = value;
    }

    public java.lang.String getId() {
        return _Id;
    }

    public void setId(java.lang.String value) {
        _Id = value;
    }

    public com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingContext context) {
        return new com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.GetAllServiceObjectAttributesRequestTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("", "className");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _ClassName), "ClassName");
        } catch (java.lang.Exception e) {
            com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "id");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _Id), "Id");
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
        return (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.GetAllServiceObjectAttributesRequestType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava"
+"/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\u0005\u00de\u00c9\u00dbppsr\u0000\'com.sun.msv.gra"
+"mmar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/m"
+"sv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000"
+"\u0003\u0002\u00ddBipp\u0000sq\u0000~\u0000\u0000\u0002\u00ddB^ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
+"\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004na"
+"met\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003\u0000s0lppsr\u0000#com.sun.ms"
+"v.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com."
+"sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun"
+".msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.dat"
+"atype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/l"
+"ang/String;L\u0000\btypeNameq\u0000~\u0000\u0013L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/data"
+"type/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3.org/2001/XML"
+"Schemat\u0000\u0006stringsr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProces"
+"sor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpa"
+"ceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Expression$"
+"NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\nppsr\u0000\u001bcom.sun.msv.util"
+".StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0013L\u0000\fnamespaceURIq\u0000~\u0000\u0013x"
+"pq\u0000~\u0000\u0017q\u0000~\u0000\u0016sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000"
+"\u0001\u0002j\u0011\u00edppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003exp"
+"q\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u0007xq\u0000~\u0000\u0003\u0002j\u0011\u00e2sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa"
+"\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psq\u0000~\u0000\u000b\u0001\u0093\u00f6\u00f2ppsr\u0000\"com.sun.msv.datatype.xsd.Qna"
+"meType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0010q\u0000~\u0000\u0016t\u0000\u0005QNamesr\u00005com.sun.msv.datatype"
+".xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0019q\u0000~\u0000\u001csq\u0000~\u0000"
+"\u001dq\u0000~\u0000(q\u0000~\u0000\u0016sr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0002L\u0000\tlocalNameq\u0000~\u0000\u0013L\u0000\fnamespaceURIq\u0000~\u0000\u0013xr\u0000\u001dcom.sun.msv.gramma"
+"r.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3.org/2001/XML"
+"Schema-instancesr\u00000com.sun.msv.grammar.Expression$EpsilonExp"
+"ression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tsq\u0000~\u0000#\u0001psq\u0000~\u0000,t\u0000\tclassNamet\u0000\u0000sq\u0000"
+"~\u0000\u0006\u0003\u0001\u0087mpp\u0000sq\u0000~\u0000\u0000\u0003\u0001\u0087bppq\u0000~\u0000\u000esq\u0000~\u0000\u001f\u0002\u008eV\u00f1ppsq\u0000~\u0000!\u0002\u008eV\u00e6q\u0000~\u0000$pq\u0000~\u0000%"
+"sq\u0000~\u0000,q\u0000~\u0000/q\u0000~\u00000q\u0000~\u00002sq\u0000~\u0000,t\u0000\u0002idq\u0000~\u00006sr\u0000\"com.sun.msv.grammar"
+".ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar"
+"/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.Express"
+"ionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthresholdL\u0000\u0006parentq\u0000"
+"~\u0000?[\u0000\u0005tablet\u0000![Lcom/sun/msv/grammar/Expression;xp\u0000\u0000\u0000\u0005\u0000\u0000\u00009pur"
+"\u0000![Lcom.sun.msv.grammar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfpppppppp"
+"pppq\u0000~\u00008ppppppppppppppppppppppppppppppq\u0000~\u0000\npppppppppppq\u0000~\u00009p"
+"ppppppppppppppppppppppppq\u0000~\u0000\u0005ppppq\u0000~\u0000 pppppppppppppppppppppp"
+"pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp"
+"ppppppppppppppppppppppp"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingContext context) {
            super(context, "-------");
        }

        protected Unmarshaller(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.GetAllServiceObjectAttributesRequestTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        if (("className" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        break;
                    case  6 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  3 :
                        if (("id" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
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
                    case  2 :
                        if (("className" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  6 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  5 :
                        if (("id" == ___local)&&("" == ___uri)) {
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
                    case  6 :
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
                    case  6 :
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
                        case  4 :
                            eatText1(value);
                            state = 5;
                            return ;
                        case  6 :
                            revertToParentFromText(value);
                            return ;
                        case  1 :
                            eatText2(value);
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
                _Id = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _ClassName = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
