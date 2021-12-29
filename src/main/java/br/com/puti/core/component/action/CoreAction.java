package br.com.puti.core.component.action;

import br.com.puti.core.component.database.mysql.DataBase;
import br.com.puti.core.component.database.response.DataBaseResponse;

public interface CoreAction<E extends Object> {
	void response(DataBaseResponse paramResponse, DataBase paramObject, E paramE);

}
