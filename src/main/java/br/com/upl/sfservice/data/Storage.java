package br.com.upl.sfservice.data;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.UriParameter;

import br.com.upl.sfservice.dao.EstoqueDao;
import br.com.upl.sfservice.model.Estoque;
import br.com.upl.sfservice.service.SFSEdmProvider;
import br.com.upl.sfservice.util.Util;

public class Storage {
	
	private List<Entity> productList;
	private EstoqueDao estoqueDao = new EstoqueDao();

    public Storage() {
        productList = new ArrayList<Entity>();
        //initSampleData();
        initEstoque();
    }

    /* PUBLIC FACADE */

    public EntityCollection readEntitySetData(EdmEntitySet edmEntitySet)throws ODataApplicationException{

        // actually, this is only required if we have more than one Entity Sets
        if(edmEntitySet.getName().equals(SFSEdmProvider.ES_ESTOQUES_NAME)){
            return getProducts();
        }

        return null;
    }

    public Entity readEntityData(EdmEntitySet edmEntitySet, List<UriParameter> keyParams) throws ODataApplicationException{

        EdmEntityType edmEntityType = edmEntitySet.getEntityType();

        // actually, this is only required if we have more than one Entity Type
        if(edmEntityType.getName().equals(SFSEdmProvider.ET_ESTOQUE_NAME)){
            return getProduct(edmEntityType, keyParams);
        }

        return null;
    }



    /*  INTERNAL */

    private EntityCollection getProducts(){
        EntityCollection retEntitySet = new EntityCollection();

        for(Entity productEntity : this.productList){
            retEntitySet.getEntities().add(productEntity);
        }

        return retEntitySet;
    }


    private Entity getProduct(EdmEntityType edmEntityType, List<UriParameter> keyParams) throws ODataApplicationException{

        // the list of entities at runtime
        EntityCollection entitySet = getProducts();

        /*  generic approach  to find the requested entity */
        Entity requestedEntity = Util.findEntity(edmEntityType, entitySet, keyParams);

        if(requestedEntity == null){
            // this variable is null if our data doesn't contain an entity for the requested key
            // Throw suitable exception
            throw new ODataApplicationException("Entity for requested key doesn't exist",
                                       HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ENGLISH);
        }

        return requestedEntity;
     }

     /* HELPER */
     private void initSampleData(){

         // add some sample product entities
         final Entity e1 = new Entity()
                               .addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 1))
                               .addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Notebook Basic 15"))
                               .addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
                                            "Notebook Basic, 1.7GHz - 15 XGA - 1024MB DDR2 SDRAM - 40GB"));
        e1.setId(createId("Products", 1));
        productList.add(e1);

        final Entity e2 = new Entity()
                              .addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 2))
                              .addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "1UMTS PDA"))
                              .addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
                                           "Ultrafast 3G UMTS/HSDPA Pocket PC, supports GSM network"));
        e2.setId(createId("Products", 1));
        productList.add(e2);

        final Entity e3 = new Entity()
                              .addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 3))
                              .addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Ergo Screen"))
                              .addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
                    "19 Optimum Resolution 1024 x 768 @ 85Hz, resolution 1280 x 960"));
        e3.setId(createId("Products", 1));
        productList.add(e3);
    }
     
     private void initEstoque(){
    	 
    	 List<Estoque> estoques = new ArrayList<Estoque>();
    	 estoques = estoqueDao.listaEstoque();
    	 int cont = 1;
         for(Estoque estoque : estoques) {
	         final Entity e1 = new Entity()
	                               .addProperty(new Property(null, "ID", ValueType.PRIMITIVE, cont++))
	                               .addProperty(new Property(null, "Material", ValueType.PRIMITIVE, estoque.getMaterial()))
	                               .addProperty(new Property(null, "MaterialDES", ValueType.PRIMITIVE,estoque.getMaterialDes()))
	                               .addProperty(new Property(null, "Unidade", ValueType.PRIMITIVE,estoque.getUnidade()))
	                               .addProperty(new Property(null, "Filial", ValueType.PRIMITIVE,estoque.getFilial()))
	                               .addProperty(new Property(null, "TipoMaterial", ValueType.PRIMITIVE,estoque.getTipoMaterial()))
	                               .addProperty(new Property(null, "Deposito", ValueType.PRIMITIVE,estoque.getDeposito()))
	                               .addProperty(new Property(null, "Lote", ValueType.PRIMITIVE,estoque.getLote()))
	                               .addProperty(new Property(null, "Estoque_Disponivel", ValueType.PRIMITIVE,estoque.getEstoqueDisponivel()))
	                               .addProperty(new Property(null, "Estoque_Qualidade", ValueType.PRIMITIVE,estoque.getEstoqueQualidade()))
	                               .addProperty(new Property(null, "Estoque_Transito", ValueType.PRIMITIVE,estoque.getEstoqueTransito()))
	                               .addProperty(new Property(null, "Estoque_Bloqueado", ValueType.PRIMITIVE,estoque.getEstoqueBloqueado()))
	                               .addProperty(new Property(null, "BASE", ValueType.PRIMITIVE,estoque.getBase()));
	        e1.setId(createId("Products", 1));
	        productList.add(e1);
         }
        
    } 

    private URI createId(String entitySetName, Object id) {
        try {
            return new URI(entitySetName + "(" + String.valueOf(id) + ")");
        } catch (URISyntaxException e) {
            throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
        }
    }

}
