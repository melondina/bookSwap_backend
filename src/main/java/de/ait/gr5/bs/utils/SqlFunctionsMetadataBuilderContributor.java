package de.ait.gr5.bs.utils;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.BooleanType;
import org.springframework.stereotype.Component;

@Component
public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {
  @Override
  public void contribute(MetadataBuilder metadataBuilder) {
    metadataBuilder.applySqlFunction("tsvector_match",
        new SQLFunctionTemplate(BooleanType.INSTANCE,
            "to_tsvector('english', coalesce(description, '') || ' ' || coalesce(title, '') || ' ' || coalesce(author, '')) @@ to_tsquery('english', ?1)"));
  }

}
