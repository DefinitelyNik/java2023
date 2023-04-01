# java2023
Непочатый Н.В. АТ-02

# Sleepytale - игра-пародия на игру Undertale
## Общая концепция игры
Игра будет представлять собой 2д платформер с незамысловатым сюжетом. Игрок будет управлять главным героем(человеком), который сможет передвигаться по локациям, интерактировать с различными предметами и npc, сражаться с враждебными npc и проходить головоломки.
У игрока будет инвентарь с оружием, предметами, восстанавливающими очки здоровья, и иным снаряжением, деньги, очки здоровья, уровень. Игрок сможет пройти игру на 2 концовки в зависимости от того, будет ли он сражаться с враждебными npc (их можно будет переубедить или просто пощадить, как и в оригинальной игре).
В игре будут точки сохранения, на которых игрок сможет возродиться в случае смерти. Также можно будет свободно перемещаться между ними.

## Взаимодействие с игровым миром
Игрок может говорить с нейтральными или дружелюбными npc, покупать различные предметы, оружие, нажимать кнопки, двигать объекты(если их можно подвинуть), выкидывать предметы из инвентаря, класть предметы в персональный сундук, сохраняться на точках сохранения, читать описания различных объектов(от обычного стула или цветка до памятника какого-нибудь) и получать удовольствие от геймплея.

## Боевая система
Игрок будет "случайно" натыкаться на враждебных npc, с которыми ему придется сражаться. При встрече с таким npc, вас перенесет в окно сражения с ним. \
Само окно будет состоять из:\
-информации об игроке и противнике(уровень, оз, имя и т.д.)\
-кнопок инвентаря, действия, боя и пощады\
-окно с изображением противника\
-окно диалога\
-окно уворота от атак npc(в нем игрок должен уворачиваться от различных атак, управляя определенной иконкой(сердцем или какой-нибудь другой))\
-окно атаки игрока(в нем игрок должен будет попасть в определенный интервал, чтобы нанести наибольшее количество урона)\

Механика боя:\
-игрок и npc могут атаковать друг друга. Атаки производятся по очереди, начиная с игрока.\
-чтобы победить, игрок должен снизить оз противника до 0 или должен с помощью действий сделать так, чтобы противника можно было пощадить\
-если оз игрока достигнет 0, он умрет\
-противник может увернуться от атаки игрока с определенным шансом(он довольно мал) или если того требует сюжет\
-игрок может убежать, но, скорее всего, его вскоре догонят(возможно, этого в игре не будет)\
-игрок может пополнить количество оз, использовав предметы из инвентаря, но в таком случае его ход будет пропущен, и он не сможет атаковать\
-игрок не может сразу же пощадить противника\

Также будет небольшое количество "боссов"(важных для сюжета npc, с которыми сражаться намного труднее, чем с обычными npc). От них нельзя убежать(только если этого не требует сюжет).\
"Случайность" столкновения с враждебным npc будет работать примерно так: будет оцениваться уровень игрока, стадия игры и время, прошедшее с последней встречи с таким npc (возможно, это не окончательный вариант работы этой системы).\

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

Use-case диаграмма
<image src="https://yandex-images.clstorage.net/VU4g7a286/042faf2K/M3r3wzgi-4YYowz_R2UCEzcDisruym7scTut1PrK3IKJwssdLELj13-kmqefahxs9QaqIStIOQsvAeHTtrWvSxM6l_Q_854X6CAMm3Nkh7eGx1p5Ddg8r9YpvqcsSD4yXQK7jW22U8L8Z042qPxpK5NhjcYvzXvbrPPTJBSHrXsCKldmrDZLdCjTTb7048NAJwYJiJ5JTE2G2syFbAssgp-gqk4tEkAzzSbpVttuuPly4a00LK8rOD4AAdV11L168hnF9-3nGcdrsj7M9iGRghTVCyudal3fZcmPFy14vDDf8Wv9LwAiUM0GqZbrTKgI8IJ8B7yfeatfA-G3RlS_yhEqtXZchOj2SEHu_kdhsjIHRPurfGoP3HdLDpY9aQ1y7iF6z1-QcUPPxZkV6Ty5eLJQnfdOnBjZzXDBBZcUn1vS2NVm70QrF_uwz061YMLjpQYIW1xqfP1muS_n32i8Ee4imOwMY8NDbfcaptrf65piwv41jr4Jan0gAlZUtQ4aMrhXhk51OAToEH19ZEOz8ZZ0ytodKP5P1Ho9ZM9oLpEdM1mMXqDykQ1Hywe6nGgY4pHftJ2Om3vegkEHJ7bPqUO6lpf_VZnmKKN8HMSD8kHVFRl7XXkP_FWIT1a_SS1RLzAozaxxoELdxPk0SP67uZDTj7csLTqqfSIThJeE_2nTqnSFDJdbt5kSrk0GQtGjhJWJms2org0U-a-33iu_0_xyum1e8MBT_pQ7pYtsKdvSUszVbe15yH1j4dS31XyKM5jX5G2UGpVr0e_PRuMikvcnuXmOGT6ud0vvRowrriG9oCjsPiDh0S8HiKar_bo54NE-1u3_ixrugbCE9ccsmmAqpVUcJSoVy4AeTFTRMAGU9sv5vXo9rSeLvXUvuA_hf4EIbr2QYnI_hTj1KD1aKvNBnsafXpjZjgOBZQR27Voy6cZFHjRo5hhRbP0GspGCJhb5e3-qvE2m-Z6336r8k">
