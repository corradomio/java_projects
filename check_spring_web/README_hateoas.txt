ResourceSupport     RepresentationModel
Resource            EntityModel
Resources           CollectionModel
PagedResources      PagedModel
ResourceAssembler   RepresentationModelAssembler
    toResource          toModel
    toResources         toCollectionModel

TypeReferences
ResponseEntity


    RepresentationModel
        EntityModel
        CollectionModel
            PagedModel


The default way to work with a [RepresentationModel] is to create a subclass of it to contain all the properties the
representation is supposed to contain, create instances of that class, populate the properties and enrich it with links.


    class TypeModel extends RepresentationModel<TypeModel> {
        ...
    }