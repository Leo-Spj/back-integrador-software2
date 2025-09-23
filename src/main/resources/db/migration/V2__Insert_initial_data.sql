-- V2__Insert_initial_data.sql

-- Insertar regiones iniciales
INSERT INTO regions (name, description) VALUES
('Lima', 'Capital del Perú, centro cultural y gastronómico.'),
('Cusco', 'Antigua capital del Imperio Inca, Patrimonio de la Humanidad.'),
('Arequipa', 'Ciudad blanca, conocida por su arquitectura colonial y el Cañón del Colca.'),
('Puno', 'Puerta de entrada al Lago Titicaca, tierra de los Uros y Taquile.'),
('Ancash', 'Región de montaña con acceso al Huascarán y la Callejón de Huaylas.'),
('Cajamarca', 'Ciudad histórica con riqueza arquitectónica y el famoso Baño del Inca.'),
('Ica', 'Región costera famosa por sus bodegas, oasis y las líneas de Nazca.'),
('Piura', 'Región norteña con playas hermosas y sitios arqueológicos.'),
('Junín', 'Región central con la laguna de Chinchaycocha y rutas de trekking.'),
('Loreto', 'Selva amazónica peruana, rica en biodiversidad y comunidades nativas.');

-- Insertar categorías iniciales
INSERT INTO categories (name, description) VALUES
('Aventura', 'Paquetes para amantes de la adrenalina y actividades al aire libre.'),
('Cultural', 'Viajes enfocados en historia, arqueología y tradiciones locales.'),
('Gastronómico', 'Experiencias culinarias que exploran la riqueza de la cocina peruana.'),
('Romántico', 'Escapadas ideales para parejas en busca de momentos especiales.'),
('Familiar', 'Viajes diseñados para toda la familia, con actividades para niños y adultos.'),
('Ecológico', 'Turismo sostenible y responsable en contacto con la naturaleza.'),
('Religioso', 'Rutas que recorren centros de peregrinación y festividades tradicionales.'),
('Negocios', 'Paquetes combinados con actividades de congresos o eventos corporativos.');

-- Insertar un usuario de prueba (clave: password123 - debes hashearla en la app)
-- NOTA: En un entorno real, NUNCA insertes contraseñas en texto plano.
INSERT INTO users (full_name, email, password_hash, phone, is_premium, points_balance) VALUES
('Usuario de Prueba', 'test@example.com', '$2a$10$examplehash...', '987654321', FALSE, 0); -- Reemplaza '$2a$10$examplehash...' con un hash real

-- Insertar un administrador de prueba (clave: admin123 - debes hashearla en la app)
-- NOTA: En un entorno real, NUNCA insertes contraseñas en texto plano.
INSERT INTO admins (username, password_hash, full_name, email, is_active) VALUES
('admin', '$2a$10$adminexamplehash...', 'Administrador Principal', 'admin@inkatravel.com', TRUE); -- Reemplaza '$2a$10$adminexamplehash...' con un hash real

-- Insertar algunos paquetes turísticos de ejemplo (requiere que existan regiones y categorías)
-- INSERT INTO tour_packages (title, description, itinerary, base_price, duration_days, duration_nights, main_image_url, region_id, category_id) VALUES
-- ('Misterios de Cusco', 'Descubre la capital del Imperio Inca...', 'Día 1: Llegada y City Tour... Día 2: Valle Sagrado...', 850.00, 4, 3, 'https://example.com/images/cusco.jpg', 2, 2),
-- ('Gastronomía en Lima', 'Explora los sabores de la capital culinaria...', 'Día 1: Degustación en Miraflores... Día 2: Barranco y cocina criolla...', 620.00, 3, 2, 'https://example.com/images/lima_food.jpg', 1, 3);
