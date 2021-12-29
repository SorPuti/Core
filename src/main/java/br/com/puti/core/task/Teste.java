package br.com.puti.core.task;

import br.com.puti.core.component.json.JsonApiId;
import br.com.puti.core.component.json.JsonApiMarshal;
import br.com.puti.core.component.json.JsonApiResource;

@JsonApiResource(name = "Teste")
public class Teste {

    @JsonApiId
    private long id = 10;

    private String name = "Teste";


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toJson(){
        return  new JsonApiMarshal().dump(this);
    }
}
