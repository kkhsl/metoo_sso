package com.metoo.nspm.dto;

import com.metoo.nspm.dto.page.PageDto;
import com.metoo.nspm.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO extends PageDto<Client> {

    private String name;
}
