REST Service

    HATEOS  https://en.wikipedia.org/wiki/HATEOAS
    HAL     https://en.wikipedia.org/wiki/Hypertext_Application_Language


    <object>.is<Type>
    <object>.as<Type>   SAME object but changing type (downcast)
    <object>.to<Type>   ANOTHER object create from this
    
Configure an object

    <object>.<propertyName>(<value>) -> this
        NOT set<PropertyName>
    
    
Builder

    Builder<Type>.newBuilder()
        .<propertyName>(<value>)
        ...
        .build() -> object of type <Type>