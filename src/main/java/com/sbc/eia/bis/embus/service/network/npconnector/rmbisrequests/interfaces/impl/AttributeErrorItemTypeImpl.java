//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2004.09.09 at 11:03:57 PDT 
//


package com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl;

public class AttributeErrorItemTypeImpl implements com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AttributeErrorItemType, com.sun.xml.bind.JAXBObject, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallableObject, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializable, com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.ValidatableObject
{

    protected boolean has_Number;
    protected long _Number;
    protected java.lang.String _Text;
    protected java.lang.String _Name;
    public final static java.lang.Class version = (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AttributeErrorItemType.class);
    }

    public long getNumber() {
        return _Number;
    }

    public void setNumber(long value) {
        _Number = value;
        has_Number = true;
    }

    public java.lang.String getText() {
        return _Text;
    }

    public void setText(java.lang.String value) {
        _Text = value;
    }

    public java.lang.String getName() {
        return _Name;
    }

    public void setName(java.lang.String value) {
        _Name = value;
    }

    public com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.UnmarshallingContext context) {
        return new com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AttributeErrorItemTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Number) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Number"));
        }
        context.startElement("", "name");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _Name), "Name");
        } catch (java.lang.Exception e) {
            com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "number");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(javax.xml.bind.DatatypeConverter.printLong(((long) _Number)), "Number");
        } catch (java.lang.Exception e) {
            com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "text");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _Text), "Text");
        } catch (java.lang.Exception e) {
            com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
    }

    public void serializeAttributes(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Number) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Number"));
        }
    }

    public void serializeURIs(com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_Number) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "Number"));
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AttributeErrorItemType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava"
+"/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\t\u00ae\u001a\u00d4ppsq\u0000~\u0000\u0000\u0007 \u00a5\u0012ppsr\u0000\'com"
+".sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst"
+"\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.Ele"
+"mentExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentMod"
+"elq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0003@\u00a5\u00d6pp\u0000sq\u0000~\u0000\u0000\u0003@\u00a5\u00cbppsr\u0000\u001bcom.sun.msv.grammar.Data"
+"Exp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exce"
+"ptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003\u0000s0lppsr"
+"\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysV"
+"alidxr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com"
+".sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceU"
+"rit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~\u0000\u0014L\u0000\nwhiteSpacet\u0000.Lcom/"
+"sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3."
+"org/2001/XMLSchemat\u0000\u0006stringsr\u00005com.sun.msv.datatype.xsd.Whit"
+"eSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype."
+"xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar"
+".Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\nppsr\u0000\u001bcom."
+"sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0014L\u0000\fnamesp"
+"aceURIq\u0000~\u0000\u0014xpq\u0000~\u0000\u0018q\u0000~\u0000\u0017sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001\u0002\u00cduZppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\bxq\u0000~\u0000\u0003\u0002\u00cduOsr\u0000\u0011java.lang.Bo"
+"olean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psq\u0000~\u0000\f\u0001\u0093\u00f6\u00f2ppsr\u0000\"com.sun.msv.data"
+"type.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0011q\u0000~\u0000\u0017t\u0000\u0005QNamesr\u00005com.sun."
+"msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~"
+"\u0000\u001aq\u0000~\u0000\u001dsq\u0000~\u0000\u001eq\u0000~\u0000)q\u0000~\u0000\u0017sr\u0000#com.sun.msv.grammar.SimpleNameCla"
+"ss\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0014L\u0000\fnamespaceURIq\u0000~\u0000\u0014xr\u0000\u001dcom.su"
+"n.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3."
+"org/2001/XMLSchema-instancesr\u00000com.sun.msv.grammar.Expressio"
+"n$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tsq\u0000~\u0000$\u0001psq\u0000~\u0000-t\u0000\u0004nam"
+"et\u0000\u0000sq\u0000~\u0000\u0007\u0003\u00df\u00ff7pp\u0000sq\u0000~\u0000\u0000\u0003\u00df\u00ff,ppsq\u0000~\u0000\f\u0001&\u00f87ppsr\u0000!com.sun.msv.dat"
+"atype.xsd.LongType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.msv.datatype.xsd.In"
+"tegerDerivedType\u0099\u00f1]\u0090&6k\u00be\u0002\u0000\u0001L\u0000\nbaseFacetst\u0000)Lcom/sun/msv/data"
+"type/xsd/XSDatatypeImpl;xq\u0000~\u0000\u0011q\u0000~\u0000\u0017t\u0000\u0004longq\u0000~\u0000+sr\u0000*com.sun.m"
+"sv.datatype.xsd.MaxInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv."
+"datatype.xsd.RangeFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\nlimitValuet\u0000\u0012Ljava/lang"
+"/Object;xr\u00009com.sun.msv.datatype.xsd.DataTypeWithValueConstr"
+"aintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.DataTypeWit"
+"hFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueCheckFlagL\u0000\bbase"
+"Typeq\u0000~\u0000=L\u0000\fconcreteTypet\u0000\'Lcom/sun/msv/datatype/xsd/Concret"
+"eType;L\u0000\tfacetNameq\u0000~\u0000\u0014xq\u0000~\u0000\u0013ppq\u0000~\u0000+\u0000\u0001sr\u0000*com.sun.msv.dataty"
+"pe.xsd.MinInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Appq\u0000~\u0000+\u0000\u0000sr\u0000$com.su"
+"n.msv.datatype.xsd.IntegerType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000<q\u0000~\u0000\u0017t\u0000\u0007integ"
+"erq\u0000~\u0000+sr\u0000,com.sun.msv.datatype.xsd.FractionDigitsFacet\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\u0005scalexr\u0000;com.sun.msv.datatype.xsd.DataTypeWithLexic"
+"alConstraintFacetT\u0090\u001c>\u001azb\u00ea\u0002\u0000\u0000xq\u0000~\u0000Dppq\u0000~\u0000+\u0001\u0000sr\u0000#com.sun.msv.d"
+"atatype.xsd.NumberType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0011q\u0000~\u0000\u0017t\u0000\u0007decimalq\u0000~\u0000+q"
+"\u0000~\u0000Pt\u0000\u000efractionDigits\u0000\u0000\u0000\u0000q\u0000~\u0000Jt\u0000\fminInclusivesr\u0000\u000ejava.lang.L"
+"ong;\u008b\u00e4\u0090\u00cc\u008f#\u00df\u0002\u0000\u0001J\u0000\u0005valuexr\u0000\u0010java.lang.Number\u0086\u00ac\u0095\u001d\u000b\u0094\u00e0\u008b\u0002\u0000\u0000xp\u0080\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000q\u0000~\u0000Jt\u0000\fmaxInclusivesq\u0000~\u0000T\u007f\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff\u00ffq\u0000~\u0000\u001dsq\u0000~\u0000\u001eq\u0000~\u0000?q\u0000~\u0000\u0017sq"
+"\u0000~\u0000 \u0002\u00b9\u0006\u00f0ppsq\u0000~\u0000\"\u0002\u00b9\u0006\u00e5q\u0000~\u0000%pq\u0000~\u0000&sq\u0000~\u0000-q\u0000~\u00000q\u0000~\u00001q\u0000~\u00003sq\u0000~\u0000-t\u0000"
+"\u0006numberq\u0000~\u00007sq\u0000~\u0000\u0007\u0002\u008du\u00bdpp\u0000sq\u0000~\u0000\u0000\u0002\u008du\u00b2ppq\u0000~\u0000\u000fsq\u0000~\u0000 \u0002\u001aEAppsq\u0000~\u0000\""
+"\u0002\u001aE6q\u0000~\u0000%pq\u0000~\u0000&sq\u0000~\u0000-q\u0000~\u00000q\u0000~\u00001q\u0000~\u00003sq\u0000~\u0000-t\u0000\u0004textq\u0000~\u00007sr\u0000\"co"
+"m.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lco"
+"m/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.ms"
+"v.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthr"
+"esholdL\u0000\u0006parentq\u0000~\u0000g[\u0000\u0005tablet\u0000![Lcom/sun/msv/grammar/Express"
+"ion;xp\u0000\u0000\u0000\b\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.grammar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n"
+"\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfppppppppppppppppppppppppppppppppppppppppppppq\u0000~\u0000Zpp"
+"pppppppppppppppppppq\u0000~\u00009pq\u0000~\u0000\u0005ppppq\u0000~\u0000`pppq\u0000~\u0000\u000bppppppppppppp"
+"pppppppppppppppppppppppppq\u0000~\u0000apppq\u0000~\u0000!pppppppppppppppppppppp"
+"pppppppppppppppppppppppppppq\u0000~\u0000\u0006pppppppppppppppppppp"));
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
            return com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AttributeErrorItemTypeImpl.this;
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
                        if (("name" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        break;
                    case  6 :
                        if (("text" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        break;
                    case  3 :
                        if (("number" == ___local)&&("" == ___uri)) {
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
                        if (("name" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  9 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  8 :
                        if (("text" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  5 :
                        if (("number" == ___local)&&("" == ___uri)) {
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
                        case  7 :
                            eatText1(value);
                            state = 8;
                            return ;
                        case  9 :
                            revertToParentFromText(value);
                            return ;
                        case  4 :
                            eatText2(value);
                            state = 5;
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
                _Text = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Number = javax.xml.bind.DatatypeConverter.parseLong(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_Number = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Name = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
