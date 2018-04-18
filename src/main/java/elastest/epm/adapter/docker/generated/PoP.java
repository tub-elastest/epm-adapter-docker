package elastest.epm.adapter.docker.generated;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client.proto

/**
 * Protobuf type {@code PoP}
 */
public  final class PoP extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:PoP)
    PoPOrBuilder {
  // Use PoP.newBuilder() to construct.
  private PoP(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PoP() {
    name_ = "";
    interfaceEndpoint_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private PoP(
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
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            interfaceEndpoint_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return Client.internal_static_PoP_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return Client.internal_static_PoP_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            PoP.class, Builder.class);
  }

  public static final int NAME_FIELD_NUMBER = 1;
  private volatile Object name_;
  /**
   * <code>optional string name = 1;</code>
   */
  public String getName() {
    Object ref = name_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>optional string name = 1;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    Object ref = name_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int INTERFACEENDPOINT_FIELD_NUMBER = 2;
  private volatile Object interfaceEndpoint_;
  /**
   * <code>optional string interfaceEndpoint = 2;</code>
   */
  public String getInterfaceEndpoint() {
    Object ref = interfaceEndpoint_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      interfaceEndpoint_ = s;
      return s;
    }
  }
  /**
   * <code>optional string interfaceEndpoint = 2;</code>
   */
  public com.google.protobuf.ByteString
      getInterfaceEndpointBytes() {
    Object ref = interfaceEndpoint_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      interfaceEndpoint_ = b;
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

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
    }
    if (!getInterfaceEndpointBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, interfaceEndpoint_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
    }
    if (!getInterfaceEndpointBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, interfaceEndpoint_);
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
    if (!(obj instanceof PoP)) {
      return super.equals(obj);
    }
    PoP other = (PoP) obj;

    boolean result = true;
    result = result && getName()
        .equals(other.getName());
    result = result && getInterfaceEndpoint()
        .equals(other.getInterfaceEndpoint());
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + INTERFACEENDPOINT_FIELD_NUMBER;
    hash = (53 * hash) + getInterfaceEndpoint().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static PoP parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static PoP parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static PoP parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static PoP parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static PoP parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static PoP parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static PoP parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static PoP parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static PoP parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static PoP parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(PoP prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code PoP}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:PoP)
      PoPOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Client.internal_static_PoP_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Client.internal_static_PoP_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              PoP.class, Builder.class);
    }

    // Construct using PoP.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      name_ = "";

      interfaceEndpoint_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return Client.internal_static_PoP_descriptor;
    }

    public PoP getDefaultInstanceForType() {
      return PoP.getDefaultInstance();
    }

    public PoP build() {
      PoP result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public PoP buildPartial() {
      PoP result = new PoP(this);
      result.name_ = name_;
      result.interfaceEndpoint_ = interfaceEndpoint_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof PoP) {
        return mergeFrom((PoP)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(PoP other) {
      if (other == PoP.getDefaultInstance()) return this;
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (!other.getInterfaceEndpoint().isEmpty()) {
        interfaceEndpoint_ = other.interfaceEndpoint_;
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
      PoP parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (PoP) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Object name_ = "";
    /**
     * <code>optional string name = 1;</code>
     */
    public String getName() {
      Object ref = name_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>optional string name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string name = 1;</code>
     */
    public Builder setName(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string name = 1;</code>
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>optional string name = 1;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private Object interfaceEndpoint_ = "";
    /**
     * <code>optional string interfaceEndpoint = 2;</code>
     */
    public String getInterfaceEndpoint() {
      Object ref = interfaceEndpoint_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        interfaceEndpoint_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>optional string interfaceEndpoint = 2;</code>
     */
    public com.google.protobuf.ByteString
        getInterfaceEndpointBytes() {
      Object ref = interfaceEndpoint_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        interfaceEndpoint_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string interfaceEndpoint = 2;</code>
     */
    public Builder setInterfaceEndpoint(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      interfaceEndpoint_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string interfaceEndpoint = 2;</code>
     */
    public Builder clearInterfaceEndpoint() {
      
      interfaceEndpoint_ = getDefaultInstance().getInterfaceEndpoint();
      onChanged();
      return this;
    }
    /**
     * <code>optional string interfaceEndpoint = 2;</code>
     */
    public Builder setInterfaceEndpointBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      interfaceEndpoint_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:PoP)
  }

  // @@protoc_insertion_point(class_scope:PoP)
  private static final PoP DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new PoP();
  }

  public static PoP getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PoP>
      PARSER = new com.google.protobuf.AbstractParser<PoP>() {
    public PoP parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PoP(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PoP> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<PoP> getParserForType() {
    return PARSER;
  }

  public PoP getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

