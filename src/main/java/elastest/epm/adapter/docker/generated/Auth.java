package elastest.epm.adapter.docker.generated;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client.proto

/** Protobuf type {@code Auth} */
public final class Auth extends com.google.protobuf.GeneratedMessageV3
    implements
    // @@protoc_insertion_point(message_implements:Auth)
    AuthOrBuilder {
  // Use Auth.newBuilder() to construct.
  private Auth(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private Auth() {
    authUrl_ = "";
    username_ = "";
    password_ = "";
    project_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }

  private Auth(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default:
            {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
          case 10:
            {
              String s = input.readStringRequireUtf8();

              authUrl_ = s;
              break;
            }
          case 18:
            {
              String s = input.readStringRequireUtf8();

              username_ = s;
              break;
            }
          case 26:
            {
              String s = input.readStringRequireUtf8();

              password_ = s;
              break;
            }
          case 34:
            {
              String s = input.readStringRequireUtf8();

              project_ = s;
              break;
            }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return Client.internal_static_Auth_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return Client.internal_static_Auth_fieldAccessorTable.ensureFieldAccessorsInitialized(
        Auth.class, Builder.class);
  }

  public static final int AUTH_URL_FIELD_NUMBER = 1;
  private volatile Object authUrl_;
  /** <code>optional string auth_url = 1;</code> */
  public String getAuthUrl() {
    Object ref = authUrl_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      authUrl_ = s;
      return s;
    }
  }
  /** <code>optional string auth_url = 1;</code> */
  public com.google.protobuf.ByteString getAuthUrlBytes() {
    Object ref = authUrl_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8((String) ref);
      authUrl_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USERNAME_FIELD_NUMBER = 2;
  private volatile Object username_;
  /** <code>optional string username = 2;</code> */
  public String getUsername() {
    Object ref = username_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      username_ = s;
      return s;
    }
  }
  /** <code>optional string username = 2;</code> */
  public com.google.protobuf.ByteString getUsernameBytes() {
    Object ref = username_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8((String) ref);
      username_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PASSWORD_FIELD_NUMBER = 3;
  private volatile Object password_;
  /** <code>optional string password = 3;</code> */
  public String getPassword() {
    Object ref = password_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      password_ = s;
      return s;
    }
  }
  /** <code>optional string password = 3;</code> */
  public com.google.protobuf.ByteString getPasswordBytes() {
    Object ref = password_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8((String) ref);
      password_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PROJECT_FIELD_NUMBER = 4;
  private volatile Object project_;
  /** <code>optional string project = 4;</code> */
  public String getProject() {
    Object ref = project_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      project_ = s;
      return s;
    }
  }
  /** <code>optional string project = 4;</code> */
  public com.google.protobuf.ByteString getProjectBytes() {
    Object ref = project_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8((String) ref);
      project_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;

  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
    if (!getAuthUrlBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, authUrl_);
    }
    if (!getUsernameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, username_);
    }
    if (!getPasswordBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, password_);
    }
    if (!getProjectBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, project_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getAuthUrlBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, authUrl_);
    }
    if (!getUsernameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, username_);
    }
    if (!getPasswordBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, password_);
    }
    if (!getProjectBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, project_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Auth)) {
      return super.equals(obj);
    }
    Auth other = (Auth) obj;

    boolean result = true;
    result = result && getAuthUrl().equals(other.getAuthUrl());
    result = result && getUsername().equals(other.getUsername());
    result = result && getPassword().equals(other.getPassword());
    result = result && getProject().equals(other.getProject());
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + AUTH_URL_FIELD_NUMBER;
    hash = (53 * hash) + getAuthUrl().hashCode();
    hash = (37 * hash) + USERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getUsername().hashCode();
    hash = (37 * hash) + PASSWORD_FIELD_NUMBER;
    hash = (53 * hash) + getPassword().hashCode();
    hash = (37 * hash) + PROJECT_FIELD_NUMBER;
    hash = (53 * hash) + getProject().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static Auth parseFrom(com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static Auth parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static Auth parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static Auth parseFrom(
      byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static Auth parseFrom(java.io.InputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static Auth parseFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static Auth parseDelimitedFrom(java.io.InputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }

  public static Auth parseDelimitedFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static Auth parseFrom(com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static Auth parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() {
    return newBuilder();
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }

  public static Builder newBuilder(Auth prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /** Protobuf type {@code Auth} */
  public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
      implements
      // @@protoc_insertion_point(builder_implements:Auth)
      AuthOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return Client.internal_static_Auth_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Client.internal_static_Auth_fieldAccessorTable.ensureFieldAccessorsInitialized(
          Auth.class, Builder.class);
    }

    // Construct using Auth.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {}
    }

    public Builder clear() {
      super.clear();
      authUrl_ = "";

      username_ = "";

      password_ = "";

      project_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return Client.internal_static_Auth_descriptor;
    }

    public Auth getDefaultInstanceForType() {
      return Auth.getDefaultInstance();
    }

    public Auth build() {
      Auth result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public Auth buildPartial() {
      Auth result = new Auth(this);
      result.authUrl_ = authUrl_;
      result.username_ = username_;
      result.password_ = password_;
      result.project_ = project_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }

    public Builder setField(com.google.protobuf.Descriptors.FieldDescriptor field, Object value) {
      return (Builder) super.setField(field, value);
    }

    public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }

    public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }

    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field, int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }

    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field, Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof Auth) {
        return mergeFrom((Auth) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(Auth other) {
      if (other == Auth.getDefaultInstance()) return this;
      if (!other.getAuthUrl().isEmpty()) {
        authUrl_ = other.authUrl_;
        onChanged();
      }
      if (!other.getUsername().isEmpty()) {
        username_ = other.username_;
        onChanged();
      }
      if (!other.getPassword().isEmpty()) {
        password_ = other.password_;
        onChanged();
      }
      if (!other.getProject().isEmpty()) {
        project_ = other.project_;
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Auth parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (Auth) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Object authUrl_ = "";
    /** <code>optional string auth_url = 1;</code> */
    public String getAuthUrl() {
      Object ref = authUrl_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        authUrl_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /** <code>optional string auth_url = 1;</code> */
    public com.google.protobuf.ByteString getAuthUrlBytes() {
      Object ref = authUrl_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        authUrl_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /** <code>optional string auth_url = 1;</code> */
    public Builder setAuthUrl(String value) {
      if (value == null) {
        throw new NullPointerException();
      }

      authUrl_ = value;
      onChanged();
      return this;
    }
    /** <code>optional string auth_url = 1;</code> */
    public Builder clearAuthUrl() {

      authUrl_ = getDefaultInstance().getAuthUrl();
      onChanged();
      return this;
    }
    /** <code>optional string auth_url = 1;</code> */
    public Builder setAuthUrlBytes(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);

      authUrl_ = value;
      onChanged();
      return this;
    }

    private Object username_ = "";
    /** <code>optional string username = 2;</code> */
    public String getUsername() {
      Object ref = username_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        username_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /** <code>optional string username = 2;</code> */
    public com.google.protobuf.ByteString getUsernameBytes() {
      Object ref = username_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        username_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /** <code>optional string username = 2;</code> */
    public Builder setUsername(String value) {
      if (value == null) {
        throw new NullPointerException();
      }

      username_ = value;
      onChanged();
      return this;
    }
    /** <code>optional string username = 2;</code> */
    public Builder clearUsername() {

      username_ = getDefaultInstance().getUsername();
      onChanged();
      return this;
    }
    /** <code>optional string username = 2;</code> */
    public Builder setUsernameBytes(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);

      username_ = value;
      onChanged();
      return this;
    }

    private Object password_ = "";
    /** <code>optional string password = 3;</code> */
    public String getPassword() {
      Object ref = password_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        password_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /** <code>optional string password = 3;</code> */
    public com.google.protobuf.ByteString getPasswordBytes() {
      Object ref = password_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        password_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /** <code>optional string password = 3;</code> */
    public Builder setPassword(String value) {
      if (value == null) {
        throw new NullPointerException();
      }

      password_ = value;
      onChanged();
      return this;
    }
    /** <code>optional string password = 3;</code> */
    public Builder clearPassword() {

      password_ = getDefaultInstance().getPassword();
      onChanged();
      return this;
    }
    /** <code>optional string password = 3;</code> */
    public Builder setPasswordBytes(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);

      password_ = value;
      onChanged();
      return this;
    }

    private Object project_ = "";
    /** <code>optional string project = 4;</code> */
    public String getProject() {
      Object ref = project_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        project_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /** <code>optional string project = 4;</code> */
    public com.google.protobuf.ByteString getProjectBytes() {
      Object ref = project_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        project_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /** <code>optional string project = 4;</code> */
    public Builder setProject(String value) {
      if (value == null) {
        throw new NullPointerException();
      }

      project_ = value;
      onChanged();
      return this;
    }
    /** <code>optional string project = 4;</code> */
    public Builder clearProject() {

      project_ = getDefaultInstance().getProject();
      onChanged();
      return this;
    }
    /** <code>optional string project = 4;</code> */
    public Builder setProjectBytes(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);

      project_ = value;
      onChanged();
      return this;
    }

    public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    // @@protoc_insertion_point(builder_scope:Auth)
  }

  // @@protoc_insertion_point(class_scope:Auth)
  private static final Auth DEFAULT_INSTANCE;

  static {
    DEFAULT_INSTANCE = new Auth();
  }

  public static Auth getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Auth> PARSER =
      new com.google.protobuf.AbstractParser<Auth>() {
        public Auth parsePartialFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
          return new Auth(input, extensionRegistry);
        }
      };

  public static com.google.protobuf.Parser<Auth> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<Auth> getParserForType() {
    return PARSER;
  }

  public Auth getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
