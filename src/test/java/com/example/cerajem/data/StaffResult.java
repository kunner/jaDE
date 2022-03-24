package com.example.cerajem.data;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

/**
 * <p>
 * Project : Rchemist Commerce Platform
 * <p>
 * Created by kunner
 **/
public class StaffResult implements Serializable {

  private List<Status> Msg;
  private List<StaffData> Data;

  public boolean isSuccess(){
    return (CollectionUtils.isEmpty(Msg) || !Msg.get(0).getSuccess()) ? false : true;
  }

  public String getErrorMessage(){
    return (CollectionUtils.isEmpty(Msg) || Msg.get(0).getSuccess()) ? "" : Msg.get(0).getError();
  }

  public List<Status> getMsg() {
    return Msg;
  }

  public void setMsg(List<Status> msg) {
    Msg = msg;
  }

  public List<StaffData> getData() {
    return Data;
  }

  public void setData(List<StaffData> data) {
    Data = data;
  }

  public class Status implements Serializable{
    private String Error;
    private Boolean Success;

    public String getError() {
      return Error;
    }

    public void setError(String error) {
      Error = error;
    }

    public Boolean getSuccess() {
      return Success;
    }

    public void setSuccess(Boolean success) {
      Success = success;
    }

  }

}
