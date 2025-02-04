package br.com.luan.api_pessoa.abstracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class BaseDTO<T> {

    // Entity entity, Class<? extends BaseDTO> dtoClass
    @SuppressWarnings("unchecked")
    public static <T> BaseDTO<T> buildFromEntity(T entity, Class<? extends BaseDTO<?>> clazz) throws Exception {
        try {
            BaseDTO<T> dto = (BaseDTO<T>) clazz.getDeclaredConstructor().newInstance();
            return dto.fromEntity(entity);

        } catch (Exception e) {
            throw new RuntimeException(String.format("Erro ao criar DTO a partir de %s", entity.getClass().getName()),
                    e);
        }
    }

    public static <T> T buildEntity(BaseDTO<T> dto) {
        return dto.toEntity();
    };

    protected abstract T toEntity();

    protected abstract BaseDTO<T> fromEntity(T entity);
}
