
INSERT INTO products (name) VALUES
    ('Milk Concentrate Protein'),
    ('Whey Protein Isolate'),
    ('Casein Protein'),
    ('Soy Protein'),
    ('Pea Protein'),
    ('Rice Protein'),
    ('Hemp Protein'),
    ('Hydrolyzed Collagen'),
    ('Multivitamin Complex'),
    ('Omega-3 Fish Oil');

INSERT INTO variants (concentration, flavor, weight) VALUES
     ('50%', 'Vanilla', '250g'),
     ('80%', 'Chocolate', '500g'),
     ('60%', 'Strawberry', '300g'),
     ('70%', 'Banana', '400g'),
     ('85%', 'Unflavored', '250g'),
     ('50%', 'Vanilla', '1000g'),
     ('75%', 'Chocolate', '850g'),
     ('45%', 'Mint', '500g'),
     ('90%', 'Cookies & Cream', '300g'),
     ('55%', 'Peanut Butter', '700g');

INSERT INTO addresses (line1, line2, city, state, zip_code) VALUES
    ('2103 N Campbell Ave', '', 'Chicago', 'IL', '60647'),
    ('504 Grant Place', '', 'Chicago', 'IL', '60614'),
    ('1234 W Belmont Ave', 'Apt 1', 'Chicago', 'IL', '60657'),
    ('1000 S Michigan Ave', 'Apt 504', 'Chicago', 'IL', '60605'),
    ('2345 N Clark St', 'Suite 200', 'Chicago', 'IL', '60614'),
    ('350 W Hubbard St', 'Suite 630', 'Chicago', 'IL', '60654'),
    ('400 N State St', '', 'Chicago', 'IL', '60654'),
    ('2300 S Throop St', 'Apt 7', 'Chicago', 'IL', '60608'),
    ('1500 S Western Ave', 'Floor 3', 'Chicago', 'IL', '60608'),
    ('780 N Aberdeen St', 'Unit 12', 'Chicago', 'IL', '60642');

INSERT INTO sample_requests (product_id, variant_id, sample_quantity, sample_application, address_id, additional_information, created_by) VALUES
      (1, 1, '2 packs', 'Product Testing', 1, 'Need ASAP', 'John Doe'),
      (2, 2, '5 packs', 'Clinical Trial', 2, 'Follow up in 2 weeks', 'Jane Smith'),
      (3, 3, '3 packs', 'Customer Sample', 3, 'Urgent', 'Emily Johnson'),
      (4, 4, '1 pack', 'Internal Review', 4, 'Include brochures', 'Mike Brown'),
      (5, 5, '4 packs', 'Promotional Event', 5, 'Extra samples', 'Jessica Lee'),
      (6, 6, '2 packs', 'Trade Show', 6, 'Deliver to booth 32', 'Chris Green'),
      (7, 7, '3 packs', 'Market Analysis', 7, 'Sensitive', 'Laura White'),
      (8, 8, '6 packs', 'Health Workshop', 8, 'Handouts included', 'Sarah Black'),
      (9, 9, '1 pack', 'Product Launch', 9, 'VIP package', 'David Wilson'),
      (10, 10, '5 packs', 'Research and Development', 10, 'Confidential', 'Karen Hill');
