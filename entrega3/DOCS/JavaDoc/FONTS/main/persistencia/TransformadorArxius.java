package FONTS.src.main.persistencia;

import FONTS.src.main.persistencia.excepcions.ErrorIntern;

public interface TransformadorArxius {
    /**
     *	Mètode per importar un arxiu amb diferents formats
     *	@param path Path de l'arxiu a importar
     *	@return Un Container amb els elements del container "setejats" amb els de l'arxiu.
     * */
    Container importar(String path) throws ErrorIntern;

    /**
     *	Mètode per exportar un arxiu amb diferents formats
     *	@param destinacio Path de destinació de l'arxiu un cop exportat.
     *	@param c Container que conté l'informació a exportar.
     *	@throws ErrorIntern
     * */
    void exportar(String destinacio, Container c) throws ErrorIntern;
}
