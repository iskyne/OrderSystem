package core;

import io.netty.handler.codec.http.FullHttpResponse;

public class HttpXmlResponse {
	private FullHttpResponse response;
	private Object body;
	
	public HttpXmlResponse(FullHttpResponse response,Object body){
		this.response=response;
		this.body=body;
	}
	public FullHttpResponse getResponse() {
		return response;
	}
	public void setResponse(FullHttpResponse response) {
		this.response = response;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}

}
