Version 2
---------

Basic object types
    Index:      array of indices
    Data:       array of values

    Series:     Index, Data
    DataFrame:  Index, Data1, Data2, ...

    Record:     <index, value>
                <index, value1, value2, ...>

Data types
    float/double    continuous
    short/int/long  discrete
    ordinal         string but ordered       -> int
    categorical     string but without order -> int
    string
    datetime        in formatted string      -> long

Index types
    int
    String
    Tuple           multidimensional index




Version 1
---------
DataFrame:
    name -> Series

Series:
    name: String
    data: scalar | array[value]
    index: Index

Data types
    float/double    continuous
    short/int/long  discrete
    ordinal         string but ordered       -> int
    categorical     string but without order -> int
    string
    datetime        in formatted string      -> long