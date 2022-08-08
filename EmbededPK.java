import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_role_tenant")
@NamedQuery(name = "UserRoleTenant.findAll", query = "SELECT u FROM UserRoleTenant u")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleTenant implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private UserRoleTenantId id;

  @Column(name = "update_by")
  private String updateBy;

  @Column(name = "update_dt_tm")
  private LocalDateTime updateDtTm;

  // bi-directional many-to-one association to Role
  // @ManyToOne
  // @JoinColumn(name = "role_id")
  // private Role role;
  @Column(name = "role_id")
  private String roleId;

  @Column(name = "country_cd")
  private String countryCd;

  @Embeddable
  @Data
  @AllArgsConstructor
  @Builder
  @NoArgsConstructor
  public static class UserRoleTenantId implements Serializable {
    // default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "tenant_id")
    private String tenantId;

  }
}
