package com.bbdigital.bbecommerce.Utils;

public class Constants {
    //Error response Code
    public final static int NOT_PRODUCTS_FOUND_CODE = 100;
    public final static int INCORRECT_DATE_FORMAT_CODE = 101;
    public final static int ERROR_GETTING_PRICES_CODE = 102;
    public final static int MISSING_PARAM_FIELDS_CODE = 103;
    public final static int INCORRECT_FORMAT_FIELDS_CODE = 104;
    public final static int UNKNOWN_ERRROR_CODE = 111;
    public final static int INCORRECT_CARD_CODE = 112;
    public final static int INSUFFICIENT_PRODUCT_STOCK = 113;

    //Error code Texts
    public static String NOT_PRODUCTS_FOUND = "No se encontraron elementos con los datos introducidos";
    public static String INCORRECT_DATE_FORMAT = "Valor incorrecto para startDate ,el valor de la fecha debe tener un formato correcto como 'uuuu-MM-dd HH:mm:ss' y tener valores validos para una fecha";
    public static String ERROR_GETTING_PRICES = "Hubo un Error al Obtener la lista de precios";
    public static String MISSING_PARAM_FIELDS = "Todos los parámetros de entrada no han sido introducidos";
    public static String INCORRECT_FORMAT_FIELDS = "Los parámetros contienen formatos incorrectos";
    public static String UNKNOWN_ERROR = "Error desconocido";
    public static String INCORRECT_CARD_MSG = "Please check the credit card details entered";
    public static String INSUFFICIENT_PRODUCT_MSG = "No hay disponibilidad suficiente del producto solicitado";
    private Constants() {}
}

