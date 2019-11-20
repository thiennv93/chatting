package org.synapse.oauth.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String code;

	public Authority(String code) {
		this.code = code;
	}

	public Authority() {

	}

	@Override
	public String getAuthority() {
		return code;
	}

}
