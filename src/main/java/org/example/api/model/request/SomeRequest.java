package org.example.api.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class SomeRequest {
	private String userName;
}
