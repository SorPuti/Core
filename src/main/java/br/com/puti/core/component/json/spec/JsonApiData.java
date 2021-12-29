package br.com.puti.core.component.json.spec;

import br.com.puti.core.component.json.resource.ResourceInformation;

import java.util.List;

public class JsonApiData {
  public final List<ResourceInformation> resourceInfoList;

  public JsonApiData(List<ResourceInformation> resourceInfoList) {
    this.resourceInfoList = resourceInfoList;
  }
}
