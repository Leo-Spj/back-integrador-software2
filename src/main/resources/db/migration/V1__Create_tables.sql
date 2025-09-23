-- V1__Create_tables.sql

-- Crear tabla de regiones
CREATE TABLE regions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla de categorías
CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla de usuarios
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    is_premium BOOLEAN DEFAULT FALSE,
    points_balance INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Crear tabla de paquetes turísticos
CREATE TABLE tour_packages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    itinerary TEXT, -- Puede almacenar JSON o texto plano
    base_price DECIMAL(10, 2) NOT NULL,
    duration_days INT NOT NULL,
    duration_nights INT NOT NULL,
    main_image_url VARCHAR(510),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    region_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (region_id) REFERENCES regions(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Crear tabla de imágenes de paquetes turísticos
CREATE TABLE tour_package_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tour_package_id BIGINT NOT NULL,
    image_url VARCHAR(510) NOT NULL,
    alt_text VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tour_package_id) REFERENCES tour_packages(id) ON DELETE CASCADE
);

-- Crear tabla de reservas
CREATE TABLE bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    tour_package_id BIGINT NOT NULL,
    travel_date_start DATE NOT NULL,
    travel_date_end DATE NOT NULL,
    number_of_travelers INT NOT NULL DEFAULT 1,
    total_price DECIMAL(10, 2) NOT NULL,
    points_used INT DEFAULT 0, -- Puntos canjeados
    points_earned INT DEFAULT 0, -- Puntos ganados
    status ENUM('PENDING', 'CONFIRMED', 'CANCELLED', 'COMPLETED') DEFAULT 'PENDING',
    booking_reference VARCHAR(50) NOT NULL UNIQUE, -- Código único de reserva
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (tour_package_id) REFERENCES tour_packages(id)
);

-- Crear tabla de historial de puntos
CREATE TABLE user_points_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    booking_id BIGINT NULL, -- Puede ser NULL si no es por reserva (ej: suscripción)
    points_change INT NOT NULL, -- Positivo para ganar, negativo para canjear
    reason VARCHAR(255) NOT NULL, -- Ej: "Compra de paquete", "Canje de descuento"
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE SET NULL
);

-- Crear tabla de administradores (opcional, si se requiere login separado para admins)
CREATE TABLE admins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- (Opcional) Crear índices para mejorar el rendimiento en búsquedas frecuentes
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_tour_packages_region ON tour_packages(region_id);
CREATE INDEX idx_tour_packages_category ON tour_packages(category_id);
CREATE INDEX idx_bookings_user ON bookings(user_id);
CREATE INDEX idx_bookings_status ON bookings(status);
CREATE INDEX idx_user_points_user ON user_points_history(user_id);
