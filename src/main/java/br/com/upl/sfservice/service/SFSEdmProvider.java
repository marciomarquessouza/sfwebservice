package br.com.upl.sfservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;

public class SFSEdmProvider extends CsdlAbstractEdmProvider {
	
	  // Service Namespace
	  public static final String NAMESPACE = "OData.SFS";

	  // EDM Container
	  public static final String CONTAINER_NAME = "Container_SFC";
	  public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

	  // Entity Types Names
	  public static final String ET_ESTOQUE_NAME = "Estoque";
	  public static final FullQualifiedName ET_ESTOQUE_FQN = new FullQualifiedName(NAMESPACE, ET_ESTOQUE_NAME);

	  // Entity Set Names
	  public static final String ES_ESTOQUES_NAME = "Estoques";
	  
	  @Override
	  public List<CsdlSchema> getSchemas() {

	    // create Schema
	    CsdlSchema schema = new CsdlSchema();
	    schema.setNamespace(NAMESPACE);

	    // add EntityTypes
	    List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
	    entityTypes.add(getEntityType(ET_ESTOQUE_FQN));
	    schema.setEntityTypes(entityTypes);

	    // add EntityContainer
	    schema.setEntityContainer(getEntityContainer());

	    // finally
	    List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
	    schemas.add(schema);

	    return schemas;
	  }


	  @Override
	  public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) {

	    // this method is called for one of the EntityTypes that are configured in the Schema
	    if(entityTypeName.equals(ET_ESTOQUE_FQN)){

	      //create EntityType properties
	      CsdlProperty id = 				new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
	      CsdlProperty material = 			new CsdlProperty().setName("Material").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      CsdlProperty materialDes = 		new CsdlProperty().setName("MaterialDES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      CsdlProperty unidade = 			new CsdlProperty().setName("Unidade").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      CsdlProperty filial = 			new CsdlProperty().setName("Filial").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      CsdlProperty tipoMaterial = 		new CsdlProperty().setName("TipoMaterial").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      CsdlProperty deposito = 			new CsdlProperty().setName("Deposito").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      CsdlProperty lote = 				new CsdlProperty().setName("Lote").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      CsdlProperty estoqueDisponivel = 	new CsdlProperty().setName("Estoque_Disponivel").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      CsdlProperty estoqueQualidade = 	new CsdlProperty().setName("Estoque_Qualidade").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      CsdlProperty estoqueTransito = 	new CsdlProperty().setName("Estoque_Transito").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      CsdlProperty estoqueBloqueado = 	new CsdlProperty().setName("Estoque_Bloqueado").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      CsdlProperty base = 				new CsdlProperty().setName("BASE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
	      
	      // create CsdlPropertyRef for Key element
	      CsdlPropertyRef propertyRef = new CsdlPropertyRef();
	      propertyRef.setName("ID");

	      // configure EntityType
	      CsdlEntityType entityType = new CsdlEntityType();
	      entityType.setName(ET_ESTOQUE_NAME);
	      entityType.setProperties(Arrays.asList(	id, 
	    		  									material , 
	    		  									materialDes,
	    		  									unidade,
	    		  									filial,
	    		  									tipoMaterial,
	    		  									deposito,
	    		  									lote,
	    		  									estoqueDisponivel,
	    		  									estoqueQualidade,
	    		  									estoqueTransito,
	    		  									estoqueBloqueado,
	    		  									base));
	      
	      entityType.setKey(Collections.singletonList(propertyRef));

	      return entityType;
	    }

	    return null;
	  }

	  @Override
	  public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {

	    if(entityContainer.equals(CONTAINER)){
	      if(entitySetName.equals(ES_ESTOQUES_NAME)){
	        CsdlEntitySet entitySet = new CsdlEntitySet();
	        entitySet.setName(ES_ESTOQUES_NAME);
	        entitySet.setType(ET_ESTOQUE_FQN);

	        return entitySet;
	      }
	    }

	    return null;
	  }

	  @Override
	  public CsdlEntityContainer getEntityContainer() {

	    // create EntitySets
	    List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
	    entitySets.add(getEntitySet(CONTAINER, ES_ESTOQUES_NAME));

	    // create EntityContainer
	    CsdlEntityContainer entityContainer = new CsdlEntityContainer();
	    entityContainer.setName(CONTAINER_NAME);
	    entityContainer.setEntitySets(entitySets);

	    return entityContainer;
	  }

	  @Override
	  public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) {

	    // This method is invoked when displaying the service document at e.g. http://localhost:8080/DemoService/DemoService.svc
	    if(entityContainerName == null || entityContainerName.equals(CONTAINER)){
	      CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
	      entityContainerInfo.setContainerName(CONTAINER);
	      return entityContainerInfo;
	    }

	    return null;
	  }

}
