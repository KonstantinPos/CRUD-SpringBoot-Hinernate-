INSERT INTO organization (version, name, full_name, inn, kpp, address, phone, is_active) VALUES
  (0, 'ПАО Сбербанк', 'Публичное акционерное общество Сбербанк России', '7707083893', '773601001', 'ул. Вавилова, д. 19', '84959575731', TRUE),
  (0, 'Банк ВТБ', ' Банк ВТБ (публичное акционерное общество)', '7702070139', '770943002', 'ул. Воронцовская, д. 43',  '84957772424', TRUE),
  (0, 'Газпромбанк', 'Газпромбанк (Акционерное общество)', '7744001497', '997950001', 'ул. Наметкина, д. 16',   '84959137474', FALSE),
  (0, 'АО Россельхозбанк', 'Акционерное общество Российский Сельскохозяйственный банк', '7725114488', '744525111', 'ул. Балчуг, 2', '4959502190', FALSE),
  (0, 'АО АЛЬФА-БАНК', 'АКЦИОНЕРНОЕ ОБЩЕСТВО АЛЬФА-БАНК', '7728168971', '770801001', 'ул. Каланчевская, д. 27',  '84957888878', TRUE);

INSERT INTO office (version, name, address, phone, is_active, organization_id) VALUES
  (0, 'Площадь Ильича', 'ул. Большая Андроньевская, д. 6', '84957397739', TRUE, 1),
  (0, 'Третьяковская', 'ул. Большая Полянка, д. 10', '84957395454', TRUE, 1),
  (0, 'Кузнецкий Мост', 'ул. Большая Лубянка, д. 16', '84957397799', TRUE, 1),
  (0, 'Полянка', ' пер. Бродников, д. 4', '84985397139', FALSE, 2),
  (0, 'Автозаводская', 'ул. Автозаводская, д. 6', '8497997740', FALSE, 3),
  (0, 'Академическая', 'ул. Дмитрия Ульянова, д. 24', '8497945748', FALSE, 4),
  (0, 'Фрунзенская', 'наб. Фрунзенская, д. 36/2', '84997397421', TRUE, 5);

INSERT INTO doc_type (version, name, code) VALUES
  (0, 'Свидетельство о рождении', '03'),
  (0, 'Военный билет', '07'),
  (0, 'Временное удостоверение,выданное взамен военного билета', '08'),
  (0, 'Паспорт иностранного гражданина', '10'),
  (0, 'Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу', '11'),
  (0, 'Вид на жительство в Российской Федерации', '12'),
  (0, 'Удостоверение беженца', '13'),
  (0, 'Разрешение на временное проживание в Российской Федерации', '15'),
  (0, 'Свидетельство о предоставлении временного убежища на территории Российской Федерации', '18'),
  (0, 'Паспорт гражданина Российской Федерации', '21'),
  (0, 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства', '23'),
  (0, 'Удостоверение личности военнослужащего Российской Федерации', '24'),
  (0, 'Иные документы', '91');

INSERT INTO country (version, name, code) VALUES
  (0, 'Украина', '380'),
  (0, 'Россия', '7'),
  (0, 'Чехия', '420'),
  (0, 'Польша', '48'),
  (0, 'Македония', '389'),
  (0, 'Мальдивы', '960'),
  (0, 'Нидерланды', '31'),
  (0, 'Соединенные Штаты Америки', '1'),
  (0, 'Тунис', '216'),
  (0, 'Турция', '90'),
  (0, 'Япония', '81'),
  (0, 'Австралия', '61'),
  (0, 'Бельгия', '32'),
  (0, 'Бразилия', '55'),
  (0, 'Венгрия', '36'),
  (0, 'Израиль', '972'),
  (0, 'Италия', '39'),
  (0, 'Канада', '1'),
  (0, 'Кения', '254');

INSERT INTO user (version, first_name, second_name, middle_name, position, phone, doc_date, is_identified, citizenship_country_id, doc_type_id, office_id)VALUES
  (0, 'Иван', 'Сергеевич', 'Суворов', 'менеджер', '89772554812', '2010-10-05', TRUE, 1, 1, 2),
  (0, 'Михаил', 'Викторович', 'Дворяшин', 'охраник', '89512554812', '2011-08-03', TRUE, 2, 2, 1),
  (0, 'Екатерина', 'Владимировна', 'Кравченко', 'кассир', '89775627741', '2005-05-05', TRUE, 3, 1, 2),
  (0, 'Мария', 'Иванова', 'Ордовская', 'консультант', '89134662543', '2015-01-25', TRUE, 2, 1, 2),
  (0, 'Александр', 'Николаевич', 'Фуковский', 'зам.директора', '89237485544', '2011-11-06', TRUE, 5, 1, 2),
  (0, 'Михаил', 'Викторович', 'Рассказов', 'кассир', '89772572125', '2001-01-11', TRUE, 4, 3, 2),
  (0, 'Максим', 'Павлович', 'Степанов', 'менеджер', '89252554812', '2015-12-29', TRUE, 5, 4, 3);


