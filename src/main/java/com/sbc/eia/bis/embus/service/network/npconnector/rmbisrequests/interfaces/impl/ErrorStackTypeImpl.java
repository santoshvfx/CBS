//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2004.09.09 at 11:03:57 PDT 
//


package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl;

public class ErrorStackTypeImpl implements com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ErrorStackType, com.sun.xml.bind.JAXBObject, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallableObject, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializable, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.ValidatableObject
{

    protected boolean has_Status;
    protected long _Status;
    protected java.lang.String _Category;
    protected com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ErrorMessageSeqType _Errors;
    public final static java.lang.Class version = (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ErrorStackType.class);
    }

    public long getStatus() {
        return _Status;
    }

    public void setStatus(long value) {
        _Status = value;
        has_Status = true;
    }

    public java.lang.String getCategory() {
        return _Category;
    }

    public void setCategory(java.lang.String value) {
        _Category = value;
    }

    public com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ErrorMessageSeqType getErrors() {
        return _Errors;
    }

    public void setErrors(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ErrorMessageSeqType value) {
        _Errors = value;
    }

    public com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingContext context) {
        return new com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.ErrorStackTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Status) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Status"));
        }
        context.startElement("", "status");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(javax.xml.bind.DatatypeConverter.printLong(((long) _Status)), "Status");
        } catch (java.lang.Exception e) {
            com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "category");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _Category), "Category");
        } catch (java.lang.Exception e) {
            com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        if (_Errors!= null) {
            context.startElement("", "errors");
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _Errors), "Errors");
            context.endNamespaceDecls();
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _Errors), "Errors");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _Errors), "Errors");
            context.endElement();
        }
    }

    public void serializeAttributes(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Status) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Status"));
        }
    }

    public void serializeURIs(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Status) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Status"));
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.ErrorStackType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava"
+"/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\b]j\rppsq\u0000~\u0000\u0000\u0005\u00a2\u00a4\u00e1ppsr\u0000\'com"
+".sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst"
+"\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.Ele"
+"mentExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentMod"
+"elq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0003\u00c9\u00f5jpp\u0000sq\u0000~\u0000\u0000\u0003\u00c9\u00f5_ppsr\u0000\u001bcom.sun.msv.grammar.Data"
+"Exp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exce"
+"ptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003\u0001&\u00f87ppsr"
+"\u0000!com.sun.msv.datatype.xsd.LongType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.ms"
+"v.datatype.xsd.IntegerDerivedType\u0099\u00f1]\u0090&6k\u00be\u0002\u0000\u0001L\u0000\nbaseFacetst\u0000)"
+"Lcom/sun/msv/datatype/xsd/XSDatatypeImpl;xr\u0000*com.sun.msv.dat"
+"atype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.dataty"
+"pe.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.X"
+"SDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;"
+"L\u0000\btypeNameq\u0000~\u0000\u0016L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/Wh"
+"iteSpaceProcessor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0004lo"
+"ngsr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u0000*com.sun.msv.datatype.xsd.MaxInclusiveFacet\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.datatype.xsd.RangeFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000"
+"\nlimitValuet\u0000\u0012Ljava/lang/Object;xr\u00009com.sun.msv.datatype.xsd"
+".DataTypeWithValueConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv."
+"datatype.xsd.DataTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012n"
+"eedValueCheckFlagL\u0000\bbaseTypeq\u0000~\u0000\u0012L\u0000\fconcreteTypet\u0000\'Lcom/sun/"
+"msv/datatype/xsd/ConcreteType;L\u0000\tfacetNameq\u0000~\u0000\u0016xq\u0000~\u0000\u0015ppq\u0000~\u0000\u001d"
+"\u0000\u0001sr\u0000*com.sun.msv.datatype.xsd.MinInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000x"
+"q\u0000~\u0000\u001fppq\u0000~\u0000\u001d\u0000\u0000sr\u0000$com.sun.msv.datatype.xsd.IntegerType\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0011q\u0000~\u0000\u0019t\u0000\u0007integerq\u0000~\u0000\u001dsr\u0000,com.sun.msv.datatype.xsd."
+"FractionDigitsFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\u0005scalexr\u0000;com.sun.msv.dataty"
+"pe.xsd.DataTypeWithLexicalConstraintFacetT\u0090\u001c>\u001azb\u00ea\u0002\u0000\u0000xq\u0000~\u0000\"pp"
+"q\u0000~\u0000\u001d\u0001\u0000sr\u0000#com.sun.msv.datatype.xsd.NumberType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000"
+"~\u0000\u0013q\u0000~\u0000\u0019t\u0000\u0007decimalq\u0000~\u0000\u001dq\u0000~\u0000.t\u0000\u000efractionDigits\u0000\u0000\u0000\u0000q\u0000~\u0000(t\u0000\fmin"
+"Inclusivesr\u0000\u000ejava.lang.Long;\u008b\u00e4\u0090\u00cc\u008f#\u00df\u0002\u0000\u0001J\u0000\u0005valuexr\u0000\u0010java.lang."
+"Number\u0086\u00ac\u0095\u001d\u000b\u0094\u00e0\u008b\u0002\u0000\u0000xp\u0080\u0000\u0000\u0000\u0000\u0000\u0000\u0000q\u0000~\u0000(t\u0000\fmaxInclusivesq\u0000~\u00002\u007f\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff"
+"\u00ffsr\u00000com.sun.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\nppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000"
+"\tlocalNameq\u0000~\u0000\u0016L\u0000\fnamespaceURIq\u0000~\u0000\u0016xpq\u0000~\u0000\u001aq\u0000~\u0000\u0019sr\u0000\u001dcom.sun.m"
+"sv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001\u0002\u00a2\u00fd#ppsr\u0000 com.sun.msv.g"
+"rammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\bxq"
+"\u0000~\u0000\u0003\u0002\u00a2\u00fd\u0018sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psq\u0000~\u0000\f\u0001\u0093"
+"\u00f6\u00f2ppsr\u0000\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0013q"
+"\u0000~\u0000\u0019t\u0000\u0005QNameq\u0000~\u0000\u001dq\u0000~\u00008sq\u0000~\u00009q\u0000~\u0000Dq\u0000~\u0000\u0019sr\u0000#com.sun.msv.gramma"
+"r.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0016L\u0000\fnamespaceURI"
+"q\u0000~\u0000\u0016xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000"
+")http://www.w3.org/2001/XMLSchema-instancesr\u00000com.sun.msv.gr"
+"ammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tsq\u0000~\u0000"
+"?\u0001psq\u0000~\u0000Ft\u0000\u0006statust\u0000\u0000sq\u0000~\u0000\u0007\u0001\u00d8\u00afrpp\u0000sq\u0000~\u0000\u0000\u0001\u00d8\u00afgppsq\u0000~\u0000\f\u0000 \u00fa\u0093ppsr"
+"\u0000)com.sun.msv.datatype.xsd.EnumerationFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0006val"
+"uest\u0000\u000fLjava/util/Set;xq\u0000~\u0000!t\u0000\'http://www.sbc.com/eia/rcl/net"
+"provisiont\u0000\fCategoryTypesr\u00005com.sun.msv.datatype.xsd.WhiteSp"
+"aceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001c\u0000\u0000sr\u0000#com.sun.msv.data"
+"type.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxq\u0000~\u0000\u0013q\u0000~\u0000\u0019t\u0000\u0006"
+"stringq\u0000~\u0000Z\u0001q\u0000~\u0000\\t\u0000\u000benumerationsr\u0000\u0011java.util.HashSet\u00baD\u0085\u0095\u0096\u00b8\u00b74"
+"\u0003\u0000\u0000xpw\f\u0000\u0000\u0000\u0010?@\u0000\u0000\u0000\u0000\u0000\u0006t\u0000\tsucceededt\u0000\ffatalFailuret\u0000\u0013succeededWi"
+"thStatust\u0000\u0012failedWaitAndRetryt\u0000\rfailedNoRetryt\u0000\u0015failedCorrec"
+"tAndRetryxq\u0000~\u00008sq\u0000~\u00009q\u0000~\u0000Xq\u0000~\u0000Wsq\u0000~\u0000;\u0001\u00b7\u00b4\u00cfppsq\u0000~\u0000=\u0001\u00b7\u00b4\u00c4q\u0000~\u0000@pq"
+"\u0000~\u0000Asq\u0000~\u0000Fq\u0000~\u0000Iq\u0000~\u0000Jq\u0000~\u0000Lsq\u0000~\u0000Ft\u0000\bcategoryq\u0000~\u0000Psq\u0000~\u0000;\u0002\u00ba\u00c5\'pps"
+"q\u0000~\u0000\u0007\u0002\u00ba\u00c5\u001cq\u0000~\u0000@p\u0000sq\u0000~\u0000\u0000\u0002\u00ba\u00c5\u0011ppsq\u0000~\u0000\u0007\u0000gT\u00f8pp\u0000sq\u0000~\u0000;\u0000gT\u00edppsr\u0000 com"
+".sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.gram"
+"mar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0000gT\u00e2q\u0000~\u0000@psq\u0000~\u0000=\u0000gT\u00df"
+"q\u0000~\u0000@psr\u00002com.sun.msv.grammar.Expression$AnyStringExpression"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\bq\u0000~\u0000Mq\u0000~\u0000wsr\u0000 com.sun.msv.grammar.AnyNa"
+"meClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Gq\u0000~\u0000Lsq\u0000~\u0000Ft\u0000^com.sbc.eia.bis.embus."
+"service.network.npconnector.rmbisrequests.interfaces.ErrorMe"
+"ssageSeqTypet\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq"
+"\u0000~\u0000;\u0002Sp\u0014ppsq\u0000~\u0000=\u0002Sp\tq\u0000~\u0000@pq\u0000~\u0000Asq\u0000~\u0000Fq\u0000~\u0000Iq\u0000~\u0000Jq\u0000~\u0000Lsq\u0000~\u0000Ft\u0000"
+"\u0006errorsq\u0000~\u0000Pq\u0000~\u0000Lsr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$Clos"
+"edHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j"
+"\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthresholdL\u0000\u0006parentq\u0000~\u0000\u0083[\u0000\u0005tablet\u0000![Lcom/"
+"sun/msv/grammar/Expression;xp\u0000\u0000\u0000\u000b\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.gram"
+"mar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfpppppppppppppppppppppppppppp"
+"pppppq\u0000~\u0000tpq\u0000~\u0000opppq\u0000~\u0000hppppq\u0000~\u0000qppppppppppppq\u0000~\u0000mpppppppppp"
+"pppppppppppppppppq\u0000~\u0000<pppppppppppppppppppppq\u0000~\u0000\u000bpppppppppppp"
+"pq\u0000~\u0000\u0006ppppppppppppppppppppppppppppppppppppppppppppq\u0000~\u0000}ppppp"
+"ppppppq\u0000~\u0000Rppppq\u0000~\u0000\u0005ppppppp"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingContext context) {
            super(context, "----------");
        }

        protected Unmarshaller(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.ErrorStackTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  0 :
                        if (("status" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        break;
                    case  3 :
                        if (("category" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        break;
                    case  7 :
                        if (("error" == ___local)&&("" == ___uri)) {
                            _Errors = ((com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.ErrorMessageSeqTypeImpl) spawnChildFromEnterElement((com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.ErrorMessageSeqTypeImpl.class), 8, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        break;
                    case  6 :
                        if (("errors" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 7;
                            return ;
                        }
                        state = 9;
                        continue outer;
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
                        if (("status" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  9 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  8 :
                        if (("errors" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  5 :
                        if (("category" == ___local)&&("" == ___uri)) {
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
                    case  9 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
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
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
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
                        case  9 :
                            revertToParentFromText(value);
                            return ;
                        case  4 :
                            eatText1(value);
                            state = 5;
                            return ;
                        case  1 :
                            eatText2(value);
                            state = 2;
                            return ;
                        case  6 :
                            state = 9;
                            continue outer;
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
                _Category = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Status = javax.xml.bind.DatatypeConverter.parseLong(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_Status = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}