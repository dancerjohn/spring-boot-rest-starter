package org.example.api.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class SomeResponse {
	private String title, description;
}
