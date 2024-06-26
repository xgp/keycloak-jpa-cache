package io.phasetwo.keycloak.jpacache.testsuite.parameters;

import com.google.common.collect.ImmutableSet;
import io.phasetwo.keycloak.compatibility.MapPublicKeyStorageProviderFactory;
import io.phasetwo.keycloak.jpacache.*;
import io.phasetwo.keycloak.jpacache.authSession.JpaCacheAuthSessionProviderFactory;
import io.phasetwo.keycloak.jpacache.loginFailure.JpaCacheLoginFailureProviderFactory;
import io.phasetwo.keycloak.jpacache.singleUseObject.JpaCacheSingleUseObjectProviderFactory;
import io.phasetwo.keycloak.jpacache.testsuite.Config;
import io.phasetwo.keycloak.jpacache.testsuite.KeycloakModelParameters;
import io.phasetwo.keycloak.jpacache.userSession.JpaCacheUserSessionProviderFactory;
import java.util.Set;
import org.keycloak.authorization.jpa.store.JPAAuthorizationStoreFactory;
import org.keycloak.broker.provider.IdentityProviderFactory;
import org.keycloak.connections.jpa.*;
import org.keycloak.connections.jpa.entityprovider.JpaEntitySpi;
import org.keycloak.connections.jpa.updater.JpaUpdaterProviderFactory;
import org.keycloak.connections.jpa.updater.JpaUpdaterSpi;
import org.keycloak.connections.jpa.updater.liquibase.conn.LiquibaseConnectionProviderFactory;
import org.keycloak.connections.jpa.updater.liquibase.conn.LiquibaseConnectionSpi;
import org.keycloak.connections.jpa.updater.liquibase.lock.LiquibaseDBLockProviderFactory;
import org.keycloak.credential.CredentialSpi;
import org.keycloak.credential.OTPCredentialProviderFactory;
import org.keycloak.credential.PasswordCredentialProviderFactory;
import org.keycloak.credential.hash.PasswordHashSpi;
import org.keycloak.credential.hash.Pbkdf2Sha256PasswordHashProviderFactory;
import org.keycloak.credential.hash.Pbkdf2Sha512PasswordHashProviderFactory;
import org.keycloak.device.DeviceRepresentationProviderFactoryImpl;
import org.keycloak.device.DeviceRepresentationSpi;
import org.keycloak.events.jpa.JpaEventStoreProviderFactory;
import org.keycloak.keys.*;
import org.keycloak.migration.MigrationProviderFactory;
import org.keycloak.migration.MigrationSpi;
import org.keycloak.models.*;
import org.keycloak.models.DeploymentStateSpi;
import org.keycloak.models.dblock.DBLockSpi;
import org.keycloak.models.jpa.*;
import org.keycloak.models.jpa.JpaDeploymentStateProviderFactory;
import org.keycloak.models.jpa.session.JpaUserSessionPersisterProviderFactory;
import org.keycloak.models.session.UserSessionPersisterSpi;
import org.keycloak.policy.*;
import org.keycloak.protocol.LoginProtocolFactory;
import org.keycloak.protocol.LoginProtocolSpi;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.Spi;
import org.keycloak.services.clientpolicy.ClientPolicyManagerSpi;
import org.keycloak.services.clientpolicy.DefaultClientPolicyManagerFactory;
import org.keycloak.services.clientregistration.policy.ClientRegistrationPolicySpi;
import org.keycloak.services.clientregistration.policy.impl.*;
import org.keycloak.sessions.AuthenticationSessionSpi;
import org.keycloak.storage.DatastoreSpi;
import org.keycloak.storage.datastore.DefaultDatastoreProviderFactory;
import org.keycloak.userprofile.DeclarativeUserProfileProviderFactory;
import org.keycloak.userprofile.UserProfileSpi;
import org.keycloak.userprofile.validator.AttributeRequiredByMetadataValidator;
import org.keycloak.userprofile.validator.BlankAttributeValidator;
import org.keycloak.userprofile.validator.BrokeringFederatedUsernameHasValueValidator;
import org.keycloak.userprofile.validator.DuplicateEmailValidator;
import org.keycloak.userprofile.validator.DuplicateUsernameValidator;
import org.keycloak.userprofile.validator.EmailExistsAsUsernameValidator;
import org.keycloak.userprofile.validator.ImmutableAttributeValidator;
import org.keycloak.userprofile.validator.MultiValueValidator;
import org.keycloak.userprofile.validator.PersonNameProhibitedCharactersValidator;
import org.keycloak.userprofile.validator.ReadOnlyAttributeUnchangedValidator;
import org.keycloak.userprofile.validator.RegistrationEmailAsUsernameEmailValueValidator;
import org.keycloak.userprofile.validator.RegistrationEmailAsUsernameUsernameValueValidator;
import org.keycloak.userprofile.validator.RegistrationUsernameExistsValidator;
import org.keycloak.userprofile.validator.UsernameHasValueValidator;
import org.keycloak.userprofile.validator.UsernameIDNHomographValidator;
import org.keycloak.userprofile.validator.UsernameMutationValidator;
import org.keycloak.userprofile.validator.UsernameProhibitedCharactersValidator;
import org.keycloak.validate.ValidatorFactory;
import org.keycloak.validate.ValidatorSPI;

public class JpaCacheParameters extends KeycloakModelParameters {

  static final Set<Class<? extends Spi>> ALLOWED_SPIS =
      ImmutableSet.<Class<? extends Spi>>builder()
          .add(AuthenticationSessionSpi.class)
          .add(ClientPolicyManagerSpi.class)
          .add(ClientRegistrationPolicySpi.class)
          .add(CredentialSpi.class)
          .add(DatastoreSpi.class)
          .add(DeploymentStateSpi.class)
          .add(DeviceRepresentationSpi.class)
          .add(DBLockSpi.class)
          .add(JpaConnectionSpi.class)
          .add(JpaEntitySpi.class)
          .add(JpaUpdaterSpi.class)
          .add(KeySpi.class)
          .add(LiquibaseConnectionSpi.class)
          .add(LoginProtocolSpi.class)
          .add(MigrationSpi.class)
          .add(PasswordHashSpi.class)
          .add(PasswordPolicyManagerSpi.class)
          .add(PasswordPolicySpi.class)
          .add(PublicKeyStorageSpi.class)
          .add(SingleUseObjectSpi.class)
          .add(UserSessionPersisterSpi.class)
          .add(UserProfileSpi.class)
          .add(ValidatorSPI.class)
          .build();

  static final Set<Class<? extends ProviderFactory>> ALLOWED_FACTORIES =
      ImmutableSet.<Class<? extends ProviderFactory>>builder()
          .add(ClientDisabledClientRegistrationPolicyFactory.class)
          .add(ClientScopesClientRegistrationPolicyFactory.class)
          .add(ConsentRequiredClientRegistrationPolicyFactory.class)
          .add(DefaultClientPolicyManagerFactory.class)
          .add(DefaultDatastoreProviderFactory.class)
          .add(DefaultJpaConnectionProviderFactory.class)
          .add(DefaultPasswordPolicyManagerProviderFactory.class)
          .add(DeviceRepresentationProviderFactoryImpl.class)
          .add(ForceExpiredPasswordPolicyProviderFactory.class)
          .add(GeneratedAesKeyProviderFactory.class)
          .add(GeneratedEcdsaKeyProviderFactory.class)
          .add(GeneratedHmacKeyProviderFactory.class)
          .add(GeneratedRsaEncKeyProviderFactory.class)
          .add(GeneratedRsaKeyProviderFactory.class)
          .add(HashAlgorithmPasswordPolicyProviderFactory.class)
          .add(HashIterationsPasswordPolicyProviderFactory.class)
          .add(HistoryPasswordPolicyProviderFactory.class)
          .add(IdentityProviderFactory.class)
          .add(ImportedRsaEncKeyProviderFactory.class)
          .add(ImportedRsaKeyProviderFactory.class)
          .add(JPAAuthorizationStoreFactory.class)
          .add(JpaCacheAuthSessionProviderFactory.class)
          .add(JpaCacheDatastoreProviderFactory.class)
          .add(JpaCacheEntityProviderFactory.class)
          .add(JpaCacheLoginFailureProviderFactory.class)
          .add(JpaCacheSingleUseObjectProviderFactory.class)
          .add(JpaCacheUserSessionProviderFactory.class)
          .add(JpaClientProviderFactory.class)
          .add(JpaClientScopeProviderFactory.class)
          .add(JpaEventStoreProviderFactory.class)
          .add(JpaGroupProviderFactory.class)
          .add(JpaRealmProviderFactory.class)
          .add(JpaRoleProviderFactory.class)
          .add(JpaUpdaterProviderFactory.class)
          .add(JpaUserProviderFactory.class)
          .add(JpaUserSessionPersisterProviderFactory.class)
          .add(LiquibaseConnectionProviderFactory.class)
          .add(LiquibaseDBLockProviderFactory.class)
          .add(JpaDeploymentStateProviderFactory.class)
          .add(LoginProtocolFactory.class)
          .add(MapPublicKeyStorageProviderFactory.class)
          .add(MaxClientsClientRegistrationPolicyFactory.class)
          .add(MigrationProviderFactory.class)
          .add(OTPCredentialProviderFactory.class)
          .add(PasswordCredentialProviderFactory.class)
          .add(Pbkdf2Sha256PasswordHashProviderFactory.class)
          .add(Pbkdf2Sha512PasswordHashProviderFactory.class)
          .add(ProtocolMappersClientRegistrationPolicyFactory.class)
          .add(ScopeClientRegistrationPolicyFactory.class)
          .add(TrustedHostClientRegistrationPolicyFactory.class)
          .add(DeclarativeUserProfileProviderFactory.class)
          .add(ValidatorFactory.class)
          .build();

  public JpaCacheParameters() {
    super(ALLOWED_SPIS, ALLOWED_FACTORIES);
  }

  @Override
  public void updateConfig(Config cf) {
    cf.spi("client")
        .defaultProvider("jpa")
        .spi("clientScope")
        .defaultProvider("jpa")
        .spi("group")
        .defaultProvider("jpa")
        .spi("role")
        .defaultProvider("jpa")
        .spi("user")
        .defaultProvider("jpa")
        .spi("realm")
        .defaultProvider("jpa")
        .spi("deploymentState")
        .defaultProvider("jpa")
        .spi("dblock")
        .defaultProvider("jpa")
        .provider(Pbkdf2Sha512PasswordHashProviderFactory.ID)
        .spi(UserProfileSpi.ID)
        .defaultProvider(DeclarativeUserProfileProviderFactory.ID)
        .spi("validator")
        .provider(BlankAttributeValidator.ID)
        .provider(AttributeRequiredByMetadataValidator.ID)
        .provider(ReadOnlyAttributeUnchangedValidator.ID)
        .provider(DuplicateUsernameValidator.ID)
        .provider(UsernameHasValueValidator.ID)
        .provider(UsernameIDNHomographValidator.ID)
        .provider(UsernameMutationValidator.ID)
        .provider(DuplicateEmailValidator.ID)
        .provider(EmailExistsAsUsernameValidator.ID)
        .provider(RegistrationEmailAsUsernameUsernameValueValidator.ID)
        .provider(RegistrationUsernameExistsValidator.ID)
        .provider(RegistrationEmailAsUsernameEmailValueValidator.ID)
        .provider(BrokeringFederatedUsernameHasValueValidator.ID)
        .provider(ImmutableAttributeValidator.ID)
        .provider(UsernameProhibitedCharactersValidator.ID)
        .provider(PersonNameProhibitedCharactersValidator.ID)
        .provider(MultiValueValidator.ID);

    cf.spi("datastore")
        .defaultProvider("legacy")
        .config("dir", "${project.build.directory:target}");
  }
}
