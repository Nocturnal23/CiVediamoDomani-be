create table if not exists public.categories
(
    category_id        bigint not null
        primary key,
    created_date       timestamp(6),
    deleted            bigint not null,
    modified_date      timestamp(6),
    url                varchar(255),
    name               varchar(255),
    father_category_id bigint
        constraint fkn5r5mo321xd2e3pkw3ocwv5iy
            references public.categories
);

alter table public.categories
    owner to tihkejoo;

create table if not exists public.users
(
    user_id          bigint       not null
        primary key,
    created_date     timestamp(6),
    deleted          bigint       not null,
    modified_date    timestamp(6),
    url              varchar(255),
    app_role         bigint,
    email            varchar(255),
    first_name       varchar(255),
    last_name        varchar(255),
    password         varchar(255),
    search_latitude  varchar(255),
    search_location  varchar(255),
    search_longitude varchar(255),
    state            varchar(255) not null
);

alter table public.users
    owner to tihkejoo;

create table if not exists public.events
(
    event_id      bigint not null
        primary key,
    created_date  timestamp(6),
    deleted       bigint not null,
    modified_date timestamp(6),
    url           varchar(255),
    coordinates   varchar(255),
    datetime      timestamp(6),
    description   varchar(255),
    image         bytea,
    place         varchar(255),
    price         real,
    title         varchar(255),
    organiser_id  bigint
        constraint fk59yigp8b4e4d9ggcdpkbvc89g
            references public.users
);

alter table public.events
    owner to tihkejoo;

create table if not exists public.event_attendees
(
    user_id  bigint not null
        constraint fk3mumymyj0ryrrywpf5ivgnf1f
            references public.users,
    event_id bigint not null
        constraint fkg0w14vgqmpawqmil4fceac4yl
            references public.events
);

alter table public.event_attendees
    owner to tihkejoo;

create table if not exists public.event_category
(
    event_id    bigint not null
        constraint fkndkv2aixwq4ibw0xboxtfqkvo
            references public.events,
    category_id bigint not null
        constraint fk97xs10d9tvhfw8up2kppg0hpd
            references public.categories
);

alter table public.event_category
    owner to tihkejoo;

create table if not exists public.user_favorites
(
    user_id  bigint not null
        constraint fk4sv7b9w9adr0fjnc4u10exlwm
            references public.users,
    event_id bigint not null
        constraint fksc9pq62im8ub9vckw39swp289
            references public.events
);

alter table public.user_favorites
    owner to tihkejoo;

