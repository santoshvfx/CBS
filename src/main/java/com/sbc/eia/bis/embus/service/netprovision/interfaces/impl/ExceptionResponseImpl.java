//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.2-b15-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.11.17 at 11:24:08 PST 
//


package com.sbc.eia.bis.embus.service.netprovision.interfaces.impl;

public class ExceptionResponseImpl
    extends com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseTypeImpl
    implements com.sbc.eia.bis.embus.service.netprovision.interfaces.ExceptionResponse, java.io.Serializable, com.sun.xml.bind.RIElement, com.sun.xml.bind.JAXBObject, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallableObject, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializable, com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.ValidatableObject
{

    private final static long serialVersionUID = 5000L;
    public final static java.lang.Class version = (com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.sbc.eia.bis.embus.service.netprovision.interfaces.ExceptionResponse.class);
    }

    public java.lang.String ____jaxb_ri____getNamespaceURI() {
        return "http://www.syndesis.com/NN/XSNN";
    }

    public java.lang.String ____jaxb_ri____getLocalName() {
        return "ExceptionResponse";
    }

    public com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context) {
        return new com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseImpl.Unmarshaller(context);
    }

    public void serializeBody(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("http://www.syndesis.com/NN/XSNN", "ExceptionResponse");
        super.serializeURIs(context);
        context.endNamespaceDecls();
        super.serializeAttributes(context);
        context.endAttributes();
        super.serializeBody(context);
        context.endElement();
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
        return (com.sbc.eia.bis.embus.service.netprovision.interfaces.ExceptionResponse.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000"
+"\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv."
+"grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000"
+"\fcontentModelt\u0000 Lcom/sun/msv/grammar/Expression;xr\u0000\u001ecom.sun."
+"msv.grammar.Expression\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilon"
+"Reducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0003xp\u000b\u009b\u00ba\u0013p"
+"p\u0000sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun."
+"msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1q\u0000~\u0000\u0003L\u0000\u0004exp2q\u0000~\u0000\u0003xq\u0000~"
+"\u0000\u0004\u000b\u009b\u00ba\bppsq\u0000~\u0000\u0007\n\u00fd\u00e3[ppsq\u0000~\u0000\u0007\t\u00c5\u0003wppsq\u0000~\u0000\u0007\b\u0006P\u00a5ppsq\u0000~\u0000\u0007\u0006\u00e5d\u00c0ppsq\u0000~"
+"\u0000\u0007\u00042\u008eLppsr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\b\u0002\t"
+"\u00c1\u00abppsq\u0000~\u0000\u0000\u0002\t\u00c1\u00a0sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000p\u0000s"
+"q\u0000~\u0000\u0007\u0002\t\u00c1\u0095ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000"
+"\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0003L\u0000\u0004namet\u0000\u001dLcom"
+"/sun/msv/util/StringPair;xq\u0000~\u0000\u0004\u0000\u0019\u0089\u00c5ppsr\u0000\'com.sun.msv.datatyp"
+"e.xsd.MaxLengthFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\tmaxLengthxr\u00009com.sun.msv.d"
+"atatype.xsd.DataTypeWithValueConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*c"
+"om.sun.msv.datatype.xsd.DataTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFac"
+"etFixedZ\u0000\u0012needValueCheckFlagL\u0000\bbaseTypet\u0000)Lcom/sun/msv/datat"
+"ype/xsd/XSDatatypeImpl;L\u0000\fconcreteTypet\u0000\'Lcom/sun/msv/dataty"
+"pe/xsd/ConcreteType;L\u0000\tfacetNamet\u0000\u0012Ljava/lang/String;xr\u0000\'com"
+".sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceU"
+"riq\u0000~\u0000\u001eL\u0000\btypeNameq\u0000~\u0000\u001eL\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype"
+"/xsd/WhiteSpaceProcessor;xpt\u0000\u001fhttp://www.syndesis.com/NN/XSN"
+"Nt\u0000\u000blong_stringsr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProces"
+"sor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpa"
+"ceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0000\u0000sr\u0000#com.sun.msv.datatype.xsd.Strin"
+"gType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xsd"
+".BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.Co"
+"ncreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001ft\u0000 http://www.w3.org/2001/XMLSche"
+"mat\u0000\u0006stringq\u0000~\u0000&\u0001q\u0000~\u0000*t\u0000\tmaxLength\u0000\u0000\u0000\u00ffsr\u00000com.sun.msv.gramma"
+"r.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004\u0000\u0000\u0000\nppsr\u0000\u001bcom"
+".sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001eL\u0000\fnames"
+"paceURIq\u0000~\u0000\u001expq\u0000~\u0000#q\u0000~\u0000\"sq\u0000~\u0000\u000f\u0001\u00f07\u00cbppsr\u0000 com.sun.msv.grammar."
+"AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0003L\u0000\tnameClassq\u0000~\u0000\u0001xq\u0000~\u0000\u0004\u0001\u00f07"
+"\u00c0q\u0000~\u0000\u0013psq\u0000~\u0000\u0015\u0000A\u009d\u0015ppsr\u0000\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000(q\u0000~\u0000+t\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.Whit"
+"eSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000%q\u0000~\u0000/sq\u0000~\u00000q\u0000~\u00008q\u0000~"
+"\u0000+sr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocal"
+"Nameq\u0000~\u0000\u001eL\u0000\fnamespaceURIq\u0000~\u0000\u001exr\u0000\u001dcom.sun.msv.grammar.NameCla"
+"ss\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-in"
+"stancesr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004\u0000\u0000\u0000\tsq\u0000~\u0000\u0012\u0001psq\u0000~\u0000<t\u0000\u0007OrderIdt\u0000\u0000q\u0000~\u0000Bsq\u0000~\u0000\u000f\u0002(\u00cc"
+"\u009cppsq\u0000~\u0000\u0000\u0002(\u00cc\u0091q\u0000~\u0000\u0013p\u0000sq\u0000~\u0000\u0007\u0002(\u00cc\u0086ppq\u0000~\u0000\u0018sq\u0000~\u0000\u000f\u0002\u000fB\u00bcppsq\u0000~\u00003\u0002\u000fB\u00b1q"
+"\u0000~\u0000\u0013pq\u0000~\u00005sq\u0000~\u0000<q\u0000~\u0000?q\u0000~\u0000@q\u0000~\u0000Bsq\u0000~\u0000<t\u0000\nActivityIdq\u0000~\u0000Fq\u0000~\u0000B"
+"sq\u0000~\u0000\u000f\u0002\u00b2\u00d6oppsq\u0000~\u0000\u0000\u0002\u00b2\u00d6dq\u0000~\u0000\u0013p\u0000sq\u0000~\u0000\u0007\u0002\u00b2\u00d6Yppsq\u0000~\u0000\u0015\u0000x|&ppsq\u0000~\u0000\u0019q"
+"\u0000~\u0000\"t\u0000\rnormal_stringq\u0000~\u0000&\u0000\u0000q\u0000~\u0000*q\u0000~\u0000*q\u0000~\u0000-\u0000\u0000\u0000@q\u0000~\u0000/sq\u0000~\u00000q\u0000~"
+"\u0000Tq\u0000~\u0000\"sq\u0000~\u0000\u000f\u0002:Z.ppsq\u0000~\u00003\u0002:Z#q\u0000~\u0000\u0013pq\u0000~\u00005sq\u0000~\u0000<q\u0000~\u0000?q\u0000~\u0000@q\u0000~\u0000"
+"Bsq\u0000~\u0000<t\u0000\bDesignIdq\u0000~\u0000Fq\u0000~\u0000Bsq\u0000~\u0000\u0000\u0001 \u00eb\u00e0pp\u0000sq\u0000~\u0000\u0007\u0001 \u00eb\u00d5ppsq\u0000~\u0000\u0000\u0000"
+"\u001e\u00ecWpp\u0000sq\u0000~\u0000\u000f\u0000\u001e\u00ecLppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0003x"
+"q\u0000~\u0000\u0004\u0000\u001e\u00ecAq\u0000~\u0000\u0013psq\u0000~\u00003\u0000\u001e\u00ec>q\u0000~\u0000\u0013psr\u00002com.sun.msv.grammar.Expre"
+"ssion$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004\u0000\u0000\u0000\bq\u0000~\u0000Cq\u0000~\u0000dsr\u0000 "
+"com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000=q\u0000~\u0000Bsq\u0000~\u0000<"
+"t\u0000Bcom.sbc.eia.bis.embus.service.netprovision.interfaces.Err"
+"orMessaget\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000"
+"\u000f\u0001\u0001\u00ffyppsq\u0000~\u00003\u0001\u0001\u00ffnq\u0000~\u0000\u0013pq\u0000~\u00005sq\u0000~\u0000<q\u0000~\u0000?q\u0000~\u0000@q\u0000~\u0000Bsq\u0000~\u0000<t\u0000\rFa"
+"ilureReasonq\u0000~\u0000Fsq\u0000~\u0000\u000f\u0001\u00be\u00b2\u00cdppsq\u0000~\u0000\u0000\u0001\u00be\u00b2\u00c2q\u0000~\u0000\u0013p\u0000sq\u0000~\u0000\u0007\u0001\u00be\u00b2\u00b7ppsq\u0000"
+"~\u0000\u0000\u0000\u001e\u00ecWpp\u0000sq\u0000~\u0000\u000f\u0000\u001e\u00ecLppsq\u0000~\u0000_\u0000\u001e\u00ecAq\u0000~\u0000\u0013psq\u0000~\u00003\u0000\u001e\u00ec>q\u0000~\u0000\u0013pq\u0000~\u0000dq"
+"\u0000~\u0000fq\u0000~\u0000Bsq\u0000~\u0000<t\u0000Icom.sbc.eia.bis.embus.service.netprovision"
+".interfaces.ErrorDetailsMessageq\u0000~\u0000isq\u0000~\u0000\u000f\u0001\u009f\u00c6[ppsq\u0000~\u00003\u0001\u009f\u00c6Pq\u0000"
+"~\u0000\u0013pq\u0000~\u00005sq\u0000~\u0000<q\u0000~\u0000?q\u0000~\u0000@q\u0000~\u0000Bsq\u0000~\u0000<t\u0000\u000eFailureDetailsq\u0000~\u0000Fq\u0000"
+"~\u0000Bsq\u0000~\u0000\u000f\u00018\u00df\u00dfppsq\u0000~\u00003\u00018\u00df\u00d4q\u0000~\u0000\u0013pq\u0000~\u0000Rsq\u0000~\u0000<t\u0000\rSchemaVersionq\u0000"
+"~\u0000Fq\u0000~\u0000Bsq\u0000~\u0000\u000f\u0000\u009d\u00d6\u00a8ppsq\u0000~\u00003\u0000\u009d\u00d6\u009dq\u0000~\u0000\u0013pq\u0000~\u00005sq\u0000~\u0000<q\u0000~\u0000?q\u0000~\u0000@q\u0000~"
+"\u0000Bsq\u0000~\u0000<t\u0000\u0011ExceptionResponseq\u0000~\u0000\"sr\u0000\"com.sun.msv.grammar.Exp"
+"ressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/Exp"
+"ressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionP"
+"ool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005countI\u0000\tthresholdL\u0000\u0006parentq\u0000~\u0000\u0087["
+"\u0000\u0005tablet\u0000![Lcom/sun/msv/grammar/Expression;xp\u0000\u0000\u0000\u001a\u0000\u0000\u00009pur\u0000![L"
+"com.sun.msv.grammar.Expression;\u00d68D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfppppppppppq\u0000"
+"~\u0000\u000epppppppppq\u0000~\u0000\tpppppppppppppppppq\u0000~\u0000\u0014pppppppq\u0000~\u0000qppppq\u0000~\u0000a"
+"q\u0000~\u0000tpppppppq\u0000~\u0000\u0010pq\u0000~\u0000^q\u0000~\u0000sppppq\u0000~\u0000oq\u0000~\u0000jpppq\u0000~\u0000Qppppppppq\u0000"
+"~\u0000\fppppppq\u0000~\u0000\npppppq\u0000~\u0000Opq\u0000~\u00002q\u0000~\u0000Vpppppppppppq\u0000~\u0000\rpppq\u0000~\u0000Ip"
+"pq\u0000~\u0000\u0081ppq\u0000~\u0000}pppppppppppppppq\u0000~\u0000Gppppppppppq\u0000~\u0000\\pppppppq\u0000~\u0000\u000b"
+"pppq\u0000~\u0000xpppppppppppppq\u0000~\u0000Jppppppppppppppppp"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context) {
            super(context, "----");
        }

        protected Unmarshaller(com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  3 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  1 :
                        attIdx = context.getAttribute("", "SchemaVersion");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().enterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        if (("OrderId" == ___local)&&("" == ___uri)) {
                            spawnHandlerFromEnterElement((((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseTypeImpl)com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseImpl.this).new Unmarshaller(context)), 2, ___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        if (("ActivityId" == ___local)&&("" == ___uri)) {
                            spawnHandlerFromEnterElement((((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseTypeImpl)com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseImpl.this).new Unmarshaller(context)), 2, ___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        if (("DesignId" == ___local)&&("" == ___uri)) {
                            spawnHandlerFromEnterElement((((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseTypeImpl)com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseImpl.this).new Unmarshaller(context)), 2, ___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        if (("FailureReason" == ___local)&&("" == ___uri)) {
                            spawnHandlerFromEnterElement((((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseTypeImpl)com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseImpl.this).new Unmarshaller(context)), 2, ___uri, ___local, ___qname, __atts);
                            return ;
                        }
                        break;
                    case  0 :
                        if (("ExceptionResponse" == ___local)&&("http://www.syndesis.com/NN/XSNN" == ___uri)) {
                            context.pushAttributes(__atts, false);
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
                    case  3 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  2 :
                        if (("ExceptionResponse" == ___local)&&("http://www.syndesis.com/NN/XSNN" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  1 :
                        attIdx = context.getAttribute("", "SchemaVersion");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveElement(___uri, ___local, ___qname);
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
                    case  3 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  1 :
                        if (("SchemaVersion" == ___local)&&("" == ___uri)) {
                            spawnHandlerFromEnterAttribute((((com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseTypeImpl)com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseImpl.this).new Unmarshaller(context)), 2, ___uri, ___local, ___qname);
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
                    case  3 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  1 :
                        attIdx = context.getAttribute("", "SchemaVersion");
                        if (attIdx >= 0) {
                            context.consumeAttribute(attIdx);
                            context.getCurrentHandler().leaveAttribute(___uri, ___local, ___qname);
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
                        case  3 :
                            revertToParentFromText(value);
                            return ;
                        case  1 :
                            attIdx = context.getAttribute("", "SchemaVersion");
                            if (attIdx >= 0) {
                                context.consumeAttribute(attIdx);
                                context.getCurrentHandler().text(value);
                                return ;
                            }
                            break;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

    }

}
