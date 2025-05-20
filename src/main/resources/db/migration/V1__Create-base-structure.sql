CREATE TABLE orders (
    id_order UUID PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    order_date TIMESTAMP NOT NULL,
    total_amount NUMERIC(19, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ
);

CREATE TABLE order_item (
    id_order_item BIGSERIAL PRIMARY KEY,
    id_product BIGSERIAL NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    unit_price NUMERIC(19, 2) NOT NULL,
    id_order UUID NOT NULL,
    FOREIGN KEY (id_order) REFERENCES orders(id_order)
);

--Trigger para atualizar data de atualizacao dos registros
CREATE OR REPLACE FUNCTION set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_timestamp
BEFORE UPDATE ON orders
FOR EACH ROW
EXECUTE FUNCTION set_timestamp();