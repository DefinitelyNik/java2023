# java2023

# Игра-пародия на игру Undertale
## Общая концепция игры
Игра будет представлять собой 2д платформер с незамысловатым сюжетом. Игрок будет управлять главным героем(человеком), который сможет передвигаться по локациям, интерактировать с различными предметами и npc, сражаться с враждебными npc и проходить головоломки.
У игрока будет инвентарь с оружием, предметами, восстанавливающими очки здоровья, и иным снаряжением, деньги, очки здоровья, уровень. Игрок сможет пройти игру на 2 концовки в зависимости от того, будет ли он сражаться с враждебными npc (их можно будет переубедить или просто пощадить, как и в оригинальной игре).
В игре будут точки сохранения, на которых игрок сможет возродиться в случае смерти. Также можно будет свободно перемещаться между ними.

## Взаимодействие с игровым миром
Игрок может говорить с нейтральными или дружелюбными npc, покупать различные предметы, оружие, нажимать кнопки, двигать объекты(если их можно подвинуть), выкидывать предметы из инвентаря, класть предметы в персональный сундук, сохраняться на точках сохранения, читать описания различных объектов(от обычного стула или цветка до памятника какого-нибудь) и получать удовольствие от геймплея.

## Боевая система
Игрок будет "случайно" натыкаться на враждебных npc, с которыми ему придется сражаться. При встрече с таким npc, вас перенесет в окно сражения с ним. 
Само окно будет состоять из:
-информации об игроке и противнике(уровень, оз, имя и т.д.)
-кнопок инвентаря, действия, боя и пощады
-окно с изображением противника
-окно диалога
-окно уворота от атак npc(в нем игрок должен уворачиваться от различных атак, управляя определенной иконкой(сердцем или какой-нибудь другой))
-окно атаки игрока(в нем игрок должен будет попасть в определенный интервал, чтобы нанести наибольшее количество урона)

Механика боя:
-игрок и npc могут атаковать друг друга. Атаки производятся по очереди, начиная с игрока.
-чтобы победить, игрок должен снизить оз противника до 0 или должен с помощью действий сделать так, чтобы противника можно было пощадить
-если оз игрока достигнет 0, он умрет
-противник может увернуться от атаки игрока с определенным шансом(он довольно мал) или если того требует сюжет
-игрок может убежать, но, скорее всего, его вскоре догонят(возможно, этого в игре не будет)
-игрок может пополнить количество оз, использовав предметы из инвентаря, но в таком случае его ход будет пропущен, и он не сможет атаковать
-игрок не может сразу же пощадить противника

Также будет небольшое количество "боссов"(важных для сюжета npc, с которыми сражаться намного труднее, чем с обычными npc). От них нельзя убежать(только если этого не требует сюжет).
"Случайность" столкновения с враждебным npc будет работать примерно так: будет оцениваться уровень игрока, стадия игры и время, прошедшее с последней встречи с таким npc (возможно, это не окончательный вариант работы этой системы).

## Головоломки
Головоломки будут встречаться на различных локациях на протяжении всей игры и будут представлять из себя довольно простые задачки, которые нужно будет решить для прохождения локации или победы над "боссом", например: нажать плитки в определенной последовательности или подобрать пароль для закрытой двери.

## Диалоги
С каждым npc можно будет поговорить. Через диалоги можно будет узнать лор игры, какие-либо факты о "боссах" и много другой бесполезной информации. В основном, диалоги в игре будут в качестве развлекательной составляющей.

## Графика
Вся игровые объекты будут нарисованы с помощью 2д графики

## Музыка и звук
В игре будут звуки, сопровождающие геймплей, и, возможно, музыка.

## Нововведения
Игра не будет полной копией Undertale. Будет иной сюжет, другие механики(возможно) и новые варианты взаимодействия с игровым миром.
