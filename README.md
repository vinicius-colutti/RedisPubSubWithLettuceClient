# Redis PubSub with Lettuce

Demo project using lettuce redis client on pub sub implementation




## Usage/Examples

First, run a redis on docker

```shell
docker network create -d bridge redisnet
docker run -d -p 6379:6379 --name myredis --network redisnet redis
```

After, let's go use the route for message production

```curl
curl --request POST \
  --url http://localhost:8080/productions \
  --header 'Content-Type: application/json' \
  --data '{
	"id": 1,
	"name": "test message"
}
```

Check if your messages are consumed in the app console

```curl
Channel: lettuceChannel
Message name: test message
```


## Authors

- [@vinicius-colutti](https://www.github.com/vinicius-colutti)

