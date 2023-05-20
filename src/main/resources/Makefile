BIS_NAME		= RM
PROPERTIES_FILE		= rm.properties

all: old_stuff external_files testclient_properties dev staging qc sat sat_prod clec prod pc eit non271eit non271sat non271prod lspse lssat lsprod

old_stuff:
	@if [[ -f make.err ]] ; then rm make.err ; fi ; \

external_files:
	cd ../external_files; 						\
	make
	cd ../external_files/testclient ;				\
	BIS_NAME=${BIS_NAME} make

testclient_properties:
	@echo "Making $@ ..."
	@if [ .${BIS_VERSION} = . ] ; then echo "BIS_VERSION undefined." ; exit 1 ; fi ; 								\
	JNDI_VERSION=`echo $$BIS_VERSION | awk -F'.' '{printf "%s.%s", $$1, $$2 }'` ;									\
	MAJOR_MINOR=`echo $$BIS_VERSION | awk -F'.' '{printf "%s_%s", $$1, $$2 }'` ;	\
	echo "JNDI_VERSION=$$JNDI_VERSION" ;				\
	echo "BIS_VERSION=${BIS_VERSION}" ;				\
	BOLD=`tput bold` ;						\
	UNBOLD=`tput sgr0` ;						\
	TESTFILES=`ls -1 testclient.properties.rm.*` ;			\
	for FILE in $$TESTFILES ;					\
	do 								\
	    echo "Generating $$FILE: \c" ;				\
	    BIS_HOME=`echo "cell/clusters/${BIS_NAME}$$JNDI_VERSION Cluster/${BIS_NAME}Home-$$JNDI_VERSION" | sed -e 's/ //g'` ;			\
	    cat $$FILE | sed -e "s,^BIS_HOME=cell/clusters.*$$,BIS_HOME=$$BIS_HOME," -e "s/,ou=[0-9]*_[0-9]*,ou=rm,o=bis/,ou=$$MAJOR_MINOR,ou=rm,o=bis/" -e "s/Rm[0-9]*.[0-9]*Soap/Rm$${JNDI_VERSION}Soap/"  > $$FILE.new ;							\
	    cmp -s $$FILE $$FILE.new ;					\
	    if [ $$? != 0 ] ;						\
	    then mv $$FILE $$FILE.old ;					\
	    	 mv $$FILE.new $$FILE ;					\
		 rm $$FILE.old ;					\
	    	 echo "$$BOLD\tUpdated$$UNBOLD" ;			\
	    else rm $$FILE.new ;					\
	    	 echo "$$BOLD\tNo Change$$UNBOLD" ;			\
	    fi ;							\
	done

dev staging qc sat sat_prod clec prod pc eit non271eit non271sat non271prod lspse lssat lsprod:
	@echo "Making ${PROPERTIES_FILE}.$@  ..."
	@if [ .${BIS_VERSION} = . ] ; then echo "BIS_VERSION undefined." ; exit 1 ; fi ; 								\
	JNDI_VERSION=`echo $$BIS_VERSION | awk -F'.' '{printf "%s.%s", $$1, $$2 }'` ;									\
	echo "JNDI_VERSION=$$JNDI_VERSION" ;				\
	echo "BIS_VERSION=${BIS_VERSION}" ;				\
      	for FILE in ${PROPERTIES_FILE} ;				\
      	do								\
		cat $$FILE.tmpl | sed -e "s/{JNDI_VERSION}/$$JNDI_VERSION/g" -e 's/{BIS_VERSION}/${BIS_VERSION}/g' | /usr/lib/cpp -D$@ > $$FILE.$@ 2>>make.err ;									\
	done 

