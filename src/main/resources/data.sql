INSERT INTO polizas (tipo_poliza, estado_poliza, fecha_inicio, fecha_fin, vigencia_mensual, canon_mensual, prima_mensual)
VALUES ('COLECTIVA', 'ACTIVO', '2026-01-01', '2027-01-01', 12, 2500000.00, 30000000.00),
       ('INDIVIDUAL', 'ACTIVO', '2026-03-01', '2027-03-01', 12, 1800000.00, 21600000.00),
       ('INDIVIDUAL', 'INACTIVO', '2025-06-01', '2025-12-01', 6, 1500000.00, 9000000.00),
       ('COLECTIVA', 'RENOVADO', '2025-10-01', '2026-10-01', 12, 3000000.00, 36000000.00);

INSERT INTO riesgos (direccion_inmueble, nombre_arrendatario, estado_riesgo, poliza_id)
VALUES ('Calle 10 # 5-20, Bogotá', 'Ana Gomez', 'ACTIVO', 1),
       ('Carrera 7 # 45-10, Bogotá', 'Juan Rodriguez', 'ACTIVO', 1),
       ('Calle 80 # 20-15, Bogotá', 'Carlos Pérez', 'ACTIVO', 2),
       ('Avenida 68 # 30-40, Bogotá', 'Laura Sanchez', 'CANCELADO', 3),
       ('Calle 127 # 15-30, Bogotá', 'Juanita Rojas', 'CANCELADO', 4),
       ('Carrera 15 # 93-60, Bogotá', 'Luis Vargas', 'ACTIVO', 4);