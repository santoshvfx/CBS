//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.11.17 at 11:24:08 PST 
//


package com.sbc.eia.bis.embus.service.netprovision.interfaces.impl;

public class RequestTypeImpl implements com.sbc.eia.bis.embus.service.netprovision.interfaces.RequestType, java.io.Serializable, com.sun.xml.bind.JAXBObject, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallableObject, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializable, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.ValidatableObject
{

    private final static long serialVersionUID = 5000L;
    protected java.lang.String _DesignId;
    protected com.sun.xml.bind.util.ListImpl _RequestItem = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
    protected java.lang.String _SchemaVersion;
    protected java.lang.String _ActivityId;
    protected java.lang.String _OrderId;
    public final static java.lang.Class version = (com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sbc.eia.bis.embus.service.netprovision.interfaces.RequestType.class);
    }

    public java.lang.String getDesignId() {
        return _DesignId;
    }

    public void setDesignId(java.lang.String value) {
        _DesignId = value;
    }

    public boolean isSetDesignId() {
        return (_DesignId!= null);
    }

    public void unsetDesignId() {
        _DesignId = null;
    }

    public java.util.List getRequestItem() {
        return _RequestItem;
    }

    public boolean isSetRequestItem() {
        return _RequestItem.isModified();
    }

    public void unsetRequestItem() {
        _RequestItem.clear();
        _RequestItem.setModified(false);
    }

    public java.lang.String getSchemaVersion() {
        if (_SchemaVersion == null) {
            return "20.0.0.002";
        } else {
            return _SchemaVersion;
        }
    }

    public void setSchemaVersion(java.lang.String value) {
        _SchemaVersion = value;
    }

    public boolean isSetSchemaVersion() {
        return (_SchemaVersion!= null);
    }

    public void unsetSchemaVersion() {
        _SchemaVersion = null;
    }

    public java.lang.String getActivityId() {
        return _ActivityId;
    }

    public void setActivityId(java.lang.String value) {
        _ActivityId = value;
    }

    public boolean isSetActivityId() {
        return (_ActivityId!= null);
    }

    public void unsetActivityId() {
        _ActivityId = null;
    }

    public java.lang.String getOrderId() {
        return _OrderId;
    }

    public void setOrderId(java.lang.String value) {
        _OrderId = value;
    }

    public boolean isSetOrderId() {
        return (_OrderId!= null);
    }

    public void unsetOrderId() {
        _OrderId = null;
    }

    public com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context) {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = _RequestItem.size();
        if (_OrderId!= null) {
            context.startElement("", "OrderId");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _OrderId), "OrderId");
            } catch (java.lang.Exception e) {
                com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_ActivityId!= null) {
            context.startElement("", "ActivityId");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _ActivityId), "ActivityId");
            } catch (java.lang.Exception e) {
                com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_DesignId!= null) {
            context.startElement("", "DesignId");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _DesignId), "DesignId");
            } catch (java.lang.Exception e) {
                com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        while (idx2 != len2) {
            context.startElement("", "RequestItem");
            int idx_6 = idx2;
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _RequestItem.get(idx_6 ++)), "RequestItem");
            context.endNamespaceDecls();
            int idx_7 = idx2;
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _RequestItem.get(idx_7 ++)), "RequestItem");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _RequestItem.get(idx2 ++)), "RequestItem");
            context.endElement();
        }
    }

    public void serializeAttributes(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = _RequestItem.size();
        if (_SchemaVersion!= null) {
            context.startAttribute("", "SchemaVersion");
            try {
                context.text(((java.lang.String) _SchemaVersion), "SchemaVersion");
            } catch (java.lang.Exception e) {
                com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endAttribute();
        }
        while (idx2 != len2) {
            idx2 += 1;
        }
    }

    public void serializeURIs(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = _RequestItem.size();
        while (idx2 != len2) {
            idx2 += 1;
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.sbc.eia.bis.embus.service.netprovision.interfaces.RequestType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava"
+"/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\u0007\u00fc\u00c2|ppsq\u0000~\u0000\u0000\u0006\u009a\u00a9`ppsq\u0000~\u0000\u0000\u0004"
+"\u00eb\u00da\u001dppsq\u0000~\u0000\u0000\u0003\u00e2\u00e7\u00b2ppsr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0000xq\u0000~\u0000\u0001\u0002/\u00a2,ppsr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom"
+".sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAtt"
+"ributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0002/\u00a2!sr\u0000\u0011java.lang.Boolean\u00cd "
+"r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000p\u0000sq\u0000~\u0000\u0000\u0002/\u00a2\u0016ppsr\u0000\u001bcom.sun.msv.grammar.Da"
+"taExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006ex"
+"ceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003\u0000\u0019\u0089\u00c5pp"
+"sr\u0000\'com.sun.msv.datatype.xsd.MaxLengthFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\tmax"
+"Lengthxr\u00009com.sun.msv.datatype.xsd.DataTypeWithValueConstrai"
+"ntFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.DataTypeWithF"
+"acet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueCheckFlagL\u0000\bbaseTy"
+"pet\u0000)Lcom/sun/msv/datatype/xsd/XSDatatypeImpl;L\u0000\fconcreteTyp"
+"et\u0000\'Lcom/sun/msv/datatype/xsd/ConcreteType;L\u0000\tfacetNamet\u0000\u0012Lj"
+"ava/lang/String;xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000\u001bL\u0000\btypeNameq\u0000~\u0000\u001bL\u0000\nwhiteSpacet"
+"\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000\u001fhttp://"
+"www.syndesis.com/NN/XSNNt\u0000\u000blong_stringsr\u00005com.sun.msv.dataty"
+"pe.xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.ms"
+"v.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0000\u0000sr\u0000#com.sun"
+".msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*c"
+"om.sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com."
+"sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001ct\u0000 http://"
+"www.w3.org/2001/XMLSchemat\u0000\u0006stringq\u0000~\u0000#\u0001q\u0000~\u0000\'t\u0000\tmaxLength\u0000\u0000\u0000"
+"\u00ffsr\u00000com.sun.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\nppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000"
+"\tlocalNameq\u0000~\u0000\u001bL\u0000\fnamespaceURIq\u0000~\u0000\u001bxpq\u0000~\u0000 q\u0000~\u0000\u001fsq\u0000~\u0000\t\u0002\u0016\u0018Lpps"
+"r\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\t"
+"nameClassq\u0000~\u0000\fxq\u0000~\u0000\u0003\u0002\u0016\u0018Aq\u0000~\u0000\u0010psq\u0000~\u0000\u0012\u0000A\u009d\u0015ppsr\u0000\"com.sun.msv.da"
+"tatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000%q\u0000~\u0000(t\u0000\u0005QNamesr\u00005com.su"
+"n.msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq"
+"\u0000~\u0000\"q\u0000~\u0000,sq\u0000~\u0000-q\u0000~\u00005q\u0000~\u0000(sr\u0000#com.sun.msv.grammar.SimpleNameC"
+"lass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001bL\u0000\fnamespaceURIq\u0000~\u0000\u001bxr\u0000\u001dcom."
+"sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w"
+"3.org/2001/XMLSchema-instancesr\u00000com.sun.msv.grammar.Express"
+"ion$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tsq\u0000~\u0000\u000f\u0001psq\u0000~\u00009t\u0000\u0007O"
+"rderIdt\u0000\u0000q\u0000~\u0000?sq\u0000~\u0000\t\u0001\u00b3E\u0081ppsq\u0000~\u0000\u000b\u0001\u00b3Evq\u0000~\u0000\u0010p\u0000sq\u0000~\u0000\u0000\u0001\u00b3Ekppq\u0000~\u0000\u0015"
+"sq\u0000~\u0000\t\u0001\u0099\u00bb\u00a1ppsq\u0000~\u00000\u0001\u0099\u00bb\u0096q\u0000~\u0000\u0010pq\u0000~\u00002sq\u0000~\u00009q\u0000~\u0000<q\u0000~\u0000=q\u0000~\u0000?sq\u0000~\u00009"
+"t\u0000\nActivityIdq\u0000~\u0000Cq\u0000~\u0000?sq\u0000~\u0000\t\u0001\b\u00f2fppsq\u0000~\u0000\u000b\u0001\b\u00f2[q\u0000~\u0000\u0010p\u0000sq\u0000~\u0000\u0000\u0001\b"
+"\u00f2Pppsq\u0000~\u0000\u0012\u0000x|&ppsq\u0000~\u0000\u0016q\u0000~\u0000\u001ft\u0000\rnormal_stringq\u0000~\u0000#\u0000\u0000q\u0000~\u0000\'q\u0000~\u0000\'"
+"q\u0000~\u0000*\u0000\u0000\u0000@q\u0000~\u0000,sq\u0000~\u0000-q\u0000~\u0000Qq\u0000~\u0000\u001fsq\u0000~\u0000\t\u0000\u0090v%ppsq\u0000~\u00000\u0000\u0090v\u001aq\u0000~\u0000\u0010pq\u0000"
+"~\u00002sq\u0000~\u00009q\u0000~\u0000<q\u0000~\u0000=q\u0000~\u0000?sq\u0000~\u00009t\u0000\bDesignIdq\u0000~\u0000Cq\u0000~\u0000?sr\u0000 com.s"
+"un.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.gramma"
+"r.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0001\u00ae\u00cf>ppsq\u0000~\u0000\u000b\u0001\u00ae\u00cf;pp\u0000sq\u0000"
+"~\u0000\u0000\u0001\u00ae\u00cf0ppsq\u0000~\u0000\u000b\u0000\u001e\u00ecWpp\u0000sq\u0000~\u0000\t\u0000\u001e\u00ecLppsq\u0000~\u0000X\u0000\u001e\u00ecAq\u0000~\u0000\u0010psq\u0000~\u00000\u0000\u001e\u00ec>"
+"q\u0000~\u0000\u0010psr\u00002com.sun.msv.grammar.Expression$AnyStringExpression"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\bq\u0000~\u0000@q\u0000~\u0000bsr\u0000 com.sun.msv.grammar.AnyNa"
+"meClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000:q\u0000~\u0000?sq\u0000~\u00009t\u0000Acom.sbc.eia.bis.embus."
+"service.netprovision.interfaces.RequestItemt\u0000+http://java.su"
+"n.com/jaxb/xjc/dummy-elementssq\u0000~\u0000\t\u0001\u008f\u00e2\u00d4ppsq\u0000~\u00000\u0001\u008f\u00e2\u00c9q\u0000~\u0000\u0010pq\u0000~"
+"\u00002sq\u0000~\u00009q\u0000~\u0000<q\u0000~\u0000=q\u0000~\u0000?sq\u0000~\u00009t\u0000\u000bRequestItemq\u0000~\u0000Csq\u0000~\u0000\t\u0001b\u0019\u0017pp"
+"sq\u0000~\u00000\u0001b\u0019\fq\u0000~\u0000\u0010pq\u0000~\u0000Osq\u0000~\u00009t\u0000\rSchemaVersionq\u0000~\u0000Cq\u0000~\u0000?sr\u0000\"com"
+".sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom"
+"/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv"
+".grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthre"
+"sholdL\u0000\u0006parentq\u0000~\u0000r[\u0000\u0005tablet\u0000![Lcom/sun/msv/grammar/Expressi"
+"on;xp\u0000\u0000\u0000\u0013\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.grammar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002"
+"\u0000\u0000xp\u0000\u0000\u0000\u00bfpppppppppppppq\u0000~\u0000hppppppppppppppppppppppppppppppppq\u0000"
+"~\u0000\bppq\u0000~\u0000mpq\u0000~\u0000_ppppppppppq\u0000~\u0000^ppppppppppppppppppq\u0000~\u0000Fpppppp"
+"pppq\u0000~\u0000\\ppppppppq\u0000~\u0000\u0006ppq\u0000~\u0000Dpq\u0000~\u0000Zpq\u0000~\u0000\u0011q\u0000~\u0000Nppppppppppppppp"
+"pppppq\u0000~\u0000\nq\u0000~\u0000Lpq\u0000~\u0000Spppppppq\u0000~\u0000Gpppppppppppppq\u0000~\u0000\u0005ppppppppp"
+"ppq\u0000~\u0000/ppppppppppppppq\u0000~\u0000\u0007ppppppppp"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context) {
            super(context, "----------------");
        }

        protected Unmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        if (("DesignId" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 10;
                            return ;
                        }
                        state = 12;
                        continue outer;
                    case  12 :
                        if (("RequestItem" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 13;
                            return ;
                        }
                        break;
                    case  15 :
                        if (("RequestItem" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 13;
                            return ;
                        }
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  6 :
                        if (("ActivityId" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        state = 9;
                        continue outer;
                    case  0 :
                        attIdx = context.getAttribute("", "SchemaVersion");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            eatText1(v);
                            state = 3;
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  3 :
                        if (("OrderId" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        state = 6;
                        continue outer;
                    case  13 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        if (("Name" == ___local)&&("" == ___uri)) {
                            _RequestItem.add(((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestItemImpl) spawnChildFromEnterElement((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestItemImpl.class), 14, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        break;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _SchemaVersion = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        state = 12;
                        continue outer;
                    case  15 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  5 :
                        if (("OrderId" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                    case  8 :
                        if (("ActivityId" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  0 :
                        attIdx = context.getAttribute("", "SchemaVersion");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            eatText1(v);
                            state = 3;
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  13 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  11 :
                        if (("DesignId" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  14 :
                        if (("RequestItem" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 15;
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
                    case  9 :
                        state = 12;
                        continue outer;
                    case  15 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  0 :
                        if (("SchemaVersion" == ___local)&&("" == ___uri)) {
                            state = 1;
                            return ;
                        }
                        state = 3;
                        continue outer;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  13 :
                        if (("Index" == ___local)&&("" == ___uri)) {
                            _RequestItem.add(((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestItemImpl) spawnChildFromEnterAttribute((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestItemImpl.class), 14, ___uri, ___local, ___qname)));
                            return ;
                        }
                        break;
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
                    case  9 :
                        state = 12;
                        continue outer;
                    case  15 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  0 :
                        attIdx = context.getAttribute("", "SchemaVersion");
                        if (attIdx >= 0) {
                            final java.lang.String v = context.eatAttribute(attIdx);
                            eatText1(v);
                            state = 3;
                            continue outer;
                        }
                        state = 3;
                        continue outer;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  13 :
                        attIdx = context.getAttribute("", "Index");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
                            return ;
                        }
                        break;
                    case  2 :
                        if (("SchemaVersion" == ___local)&&("" == ___uri)) {
                            state = 3;
                            return ;
                        }
                        break;
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
                            eatText2(value);
                            state = 5;
                            return ;
                        case  9 :
                            state = 12;
                            continue outer;
                        case  15 :
                            revertToParentFromText(value);
                            return ;
                        case  7 :
                            eatText3(value);
                            state = 8;
                            return ;
                        case  6 :
                            state = 9;
                            continue outer;
                        case  10 :
                            eatText4(value);
                            state = 11;
                            return ;
                        case  0 :
                            attIdx = context.getAttribute("", "SchemaVersion");
                            if (attIdx >= 0) {
                                final java.lang.String v = context.eatAttribute(attIdx);
                                eatText1(v);
                                state = 3;
                                continue outer;
                            }
                            state = 3;
                            continue outer;
                        case  3 :
                            state = 6;
                            continue outer;
                        case  13 :
                            attIdx = context.getAttribute("", "Index");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                        case  1 :
                            eatText1(value);
                            state = 2;
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _OrderId = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _ActivityId = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText4(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _DesignId = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
